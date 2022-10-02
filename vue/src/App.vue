<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { ref, provide, nextTick } from 'vue'
export default {
  name: 'App',
  components: {
  },
  setup() {
    const isRouterAlive  = ref(true);
    const reload = () => {
      isRouterAlive.value = false;
      nextTick(() => {
        isRouterAlive.value = true
      })
    }
    provide("reload", reload);
    return { isRouterAlive }
  }
}

</script>
<style lang="less">
ul {
  list-style: none;
}
a {
  text-decoration: none;
}
* {
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Microsoft YaHei", "Helvetica", "Tahoma", "Geneva", "Arial", sans-serif;
}
</style>
