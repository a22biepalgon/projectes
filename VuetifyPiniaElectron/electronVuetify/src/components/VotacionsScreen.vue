<template>
 Hola
</template>
<script>
import { useAppStore } from '@/store/app'
const pinia = useAppStore();

export default {
  name: "Login",
  data() {
    return {
      username: "",
    };
  },
  methods: {
  },
};
</script>