<template>
    <v-container>
        <v-row>
            <v-col cols="3"></v-col>
            <v-col cols="6">
                <div class="capsa_login">
                    <form ref="form" @submit.prevent="login()">
                        <v-text v-if="malament">
                            <p style="color: red;">Usuari o contrasenya incorrectes</p>
                        </v-text>
                            <v-text-field
                              v-model="username"
                              name="usuari"
                              label="usuari"
                              type="text"
                              placeholder="usuari"
                              required
                           ></v-text-field>
                           
                            <v-text-field
                              v-model="password"
                              name="contrasenya"
                              label="contrasenya"
                              type="password"
                              placeholder="contrasenya"
                              required
                           ></v-text-field>
                           <v-btn type="submit" class="mt-4 boto" color="primary" value="log in">Accedir-hi</v-btn>
                      </form>
                </div>
            </v-col>
            <v-col cols="3"></v-col>
        </v-row>
    </v-container>
</template>
<style>
.capsa_login{
    border: 1px solid black;
    border-radius: 20px;
    background-color: rgb(0, 110, 255);
    padding: 20%;
    text-align: center;
}
.boto:hover{
    transition: 1s !important;
    background-color: rgb(0, 255, 179) !important;
}
</style>



<script>
import { useAppStore } from '@/store/app'
const pinia = useAppStore();

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      malament: false,
    };
  },
  methods: {
    login() {
    if(this.username == "user" && this.password== "123"){
        pinia.setLoginInfo(true, this.username, "/perfilUser.png")
        this.$router.push({ path: '/votacions' })
    }else{
        this.password = "";
        this.malament = true;
    }
      const { username } = this;
      console.log(username + "logged in")
    },
  },
};

</script>