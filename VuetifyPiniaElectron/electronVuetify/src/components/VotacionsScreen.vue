<template>
  <v-dialog v-model="showAlert" width="auto">
    <v-card title="Pagina no accessible">
      <v-card-text>
        Has de fer login per a veure aquesta pagina
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn text="Login" @click="this.$router.push({ path: '/' })"></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <v-container v-if="!showAlert">
      <v-row>
        <v-col cols ="6">
          <botonsVotacio />
        </v-col>
        <v-col cols="6"></v-col>
      </v-row>
    Hola {{ this.username }}
  </v-container>
</template>
<script>
import { useAppStore } from '@/store/app'
const pinia = useAppStore();

export default {
  name: "Votacions",
  data() {
    return {
      showAlert: true,
      username: "",
      image: "",
      dadesLogin: pinia.getLoginInfo(),

    };
  },
  methods: {
    logged() {
      if (!pinia.isLoggedIn()) {
        this.showAlert = true
      } else {
        this.showAlert = false
        this.username = this.dadesLogin.username
        this.image = this.dadesLogin.image
      }
    },

  },
  created() {
    this.logged();
  },
};
</script>
<script setup>
import botonsVotacio from '@/components/botonsVotacions.vue'

</script>