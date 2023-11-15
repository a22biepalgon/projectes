<template>
  <v-layout>
  <v-app>
    <default-bar />
    <default-view />
    <default-footer />
  </v-app>
</v-layout>
</template>

<script setup>
  import DefaultBar from './AppBar.vue'
  import DefaultView from '../../components/PortadaInicial.vue'
  import DefaultFooter from './Footer.vue'
</script>
