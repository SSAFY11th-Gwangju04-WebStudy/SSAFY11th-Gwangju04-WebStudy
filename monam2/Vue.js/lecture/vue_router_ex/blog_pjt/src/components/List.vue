<template>
  <div v-for="(item,i) in data">
    <h5>{{item.title}}</h5>
    <p>{{item.date}}</p>
    <router-link :item="item" :to="`/detail/${i}`">클릭</router-link>
  </div>

</template>

<script setup>
const props = defineProps({
  data: Array,
});
</script>

<style></style>
