<template>
    <v-layout>
        <v-app-bar style="color: black;" :elevation="2" title="Llibres">
            <template v-slot:append>


                <router-link to="/">
                    <v-btn>
                        Inici
                    </v-btn>
                </router-link>



                <router-link to="/llibres"><v-btn>
                        Veure LLibres
                    </v-btn></router-link>
            </template>
        </v-app-bar>
    </v-layout>
</template>

<script setup>
//
</script>
