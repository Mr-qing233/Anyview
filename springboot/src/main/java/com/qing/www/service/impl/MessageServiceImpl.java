package com.qing.www.service.impl;

import com.qing.www.entity.Message;
import com.qing.www.mapper.MessageMapper;
import com.qing.www.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
