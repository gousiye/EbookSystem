<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>


<style scoped>
.ul {
  overflow-x: auto;
  display: -webkit-box;
  -webkit-overflow-scrolling: touch;
  margin: 0.5rem 0.5rem
}
</style>