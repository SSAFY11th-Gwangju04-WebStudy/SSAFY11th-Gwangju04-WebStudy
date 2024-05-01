<template>
  <input type="text" v-model="exampleComputed">
  <div>{{ exampleComputed }}</div>
</template>

<script setup>
import { ref, computed } from "vue";
const data = ref("");
const exampleComputed = computed({
  get() {
    return data.value
  },
  set(newData) {
    data.value = newData;
  }
})
</script>
<style scoped></style>
