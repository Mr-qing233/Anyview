/**
 * 封装socket方法
 */
/*
 * @Descripttion: 封装socket方法
 * @version:
 * @Date: 2021-08-06 11:14:39
 * @LastEditTime: 2021-10-26 14:06:34
 */
import { ElMessage } from 'element-plus'
import {store} from "@/vuex/store";



interface socket {
    websocket: any
    connectURL: string
    socket_open: boolean
    heartbeat_timer: any
    heartbeat_interval: number
    is_reconnect: boolean
    reconnect_count: number
    reconnect_current: number
    reconnect_number: number
    reconnect_timer: any
    reconnect_interval: number
    cardId: any,
    init: (receiveMessage: Function | null) => any
    receive: (message: any) => void
    heartbeat: () => void
    send: (data: any, callback?: any) => void
    close: () => void
    reconnect: () => void
}

const socket: socket = {
    websocket: null,
    connectURL: 'ws://192.168.101.145:8081/client/',
    // 开启标识
    socket_open: false,
    // 心跳timer
    heartbeat_timer: null,
    // 心跳发送频率
    heartbeat_interval: 45000,
    // 是否自动重连
    is_reconnect: true,
    // 重连次数
    reconnect_count: 3,
    // 已发起重连次数
    reconnect_current: 1,
    // 网络错误提示此时
    reconnect_number: 0,
    cardId: null,
    // 重连timer
    reconnect_timer: null,
    // 重连频率
    reconnect_interval: 5000,

    init: (receiveMessage: Function | null) => {
        if (!('WebSocket' in window)) {
            ElMessage.warning('浏览器不支持WebSocket')
            return null
        }
        // 已经创建过连接不再重复创建
        // if (socket.websocket) {
        //   return socket.websocket
        // }

        socket.websocket = new WebSocket(socket.connectURL+socket.cardId)
        socket.websocket.onmessage = (e: any) => {
            if (receiveMessage) {
                receiveMessage(e)
            }
        }

        socket.websocket.onclose = (e: any) => {
            clearInterval(socket.heartbeat_interval)
            socket.socket_open = false

            // 需要重新连接
            if (socket.is_reconnect) {
                socket.reconnect_timer = setTimeout(() => {
                    // 超过重连次数
                    if (socket.reconnect_current > socket.reconnect_count) {
                        clearTimeout(socket.reconnect_timer)
                        socket.is_reconnect = false
                        return
                    }

                    // 记录重连次数
                    socket.reconnect_current++
                    socket.reconnect()
                }, socket.reconnect_interval)
            }
        }

        // 连接成功
        socket.websocket.onopen = function() {
            console.log("WebSocket开启成功")
            socket.socket_open = true
            socket.is_reconnect = true
            // 开启心跳
            // socket.heartbeat()
        }

        // 连接发生错误
        socket.websocket.onerror = function() {}
    },

    send: (data, callback = null) => {
        // 开启状态直接发送
        if (socket.websocket.readyState === socket.websocket.OPEN) {
            socket.websocket.send(data)
            if (callback) {
                callback()
            }

            // 正在开启状态，则等待1s后重新调用
        } else {
            clearInterval(socket.heartbeat_timer)
            if (socket.reconnect_number < 1) {
                ElMessage({
                    type: 'error',
                    message: "opening",
                    duration: 0,
                })
            }
            socket.reconnect_number++
        }
    },

    receive: (message: any) => {
        let params = JSON.parse(message.data)
        params = JSON.parse(params)
        return params
    },

    heartbeat: () => {
        if (socket.heartbeat_timer) {
            clearInterval(socket.heartbeat_timer)
        }

        socket.heartbeat_timer = setInterval(() => {
            let data = {
                cardId: store.state.userInfo.cardId,
                content: 'ping',
            }
            const sendDara = {
                encryption_type: 'base64',
                data: JSON.stringify(data),
            };
            socket.send(sendDara)
        }, socket.heartbeat_interval)
    },

    close: () => {
        clearInterval(socket.heartbeat_interval)
        socket.is_reconnect = false
        socket.websocket.close()
    },

    /**
     * 重新连接
     */
    reconnect: () => {
        if (socket.websocket && !socket.is_reconnect) {
            socket.close()
        }

        socket.init(null)
    },
}

export default socket

