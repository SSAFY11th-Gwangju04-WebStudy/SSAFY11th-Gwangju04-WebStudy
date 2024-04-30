<template>
  <div>
    <div v-for="(item, index) in array">{{ `${item}, ${index}` }}</div>
    <div v-for="obj in objs" :key="obj.id">{{ obj.name }}</div>
  </div>
</template>

<script setup>
const array = ["a", "b", "c", "d"];
const objs = [
  {
    id: 0,
    name: "john",
  },
  { id: 1, name: "anne" },
  { id: 2, name: "mary" },
  { id: 3, name: "jane" },
  { id: 4, name: "jk" },
];
</script>

<style scoped></style>
