<template>
  <div>
    <p>상세 페이지임 : {{$route.params.id}}</p>
    <p>{{ props.data[$route.params.id].title }}</p>
  </div>
</template>

<script setup>
const props = defineProps({
  data: Array,
});
console.log(props.data);

// const param = $route.params;
// console.log(param)
</script>

<style scoped></style>
