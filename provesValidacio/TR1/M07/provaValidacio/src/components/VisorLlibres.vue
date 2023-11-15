<template>
    <v-layout class="rounded rounded-md marges">
        <v-main class=" align-center justify-center prova" style="min-height: 300px;">
            <v-container>
                <v-row>
                    <v-col v-for="llibre in llibres" cols="4">
                    <v-card class="card">
                        <v-img :src="llibre.cover"></v-img>
                        <v-card-title>
                            {{ llibre.title }}
                        </v-card-title>
                        <v-card-subtitle>
                            {{ llibre.author }}
                        </v-card-subtitle>
                        <v-card-text>
                        </v-card-text>
                    </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
    </v-layout>
</template>
<style>
.card {
    width: 300px;
}
.marges{
    top: 100px;
    margin-bottom: 100px;
    bottom: 100px;
}
</style>
<script>
import { getLibros } from '@/communicationsManager';
export default {
    data: () => ({
        bg: "rgb(255,255,255)",
        llibres: [],
        dataAvui: Date(),
    }),
    
    created(){
        this.getLlibres();
    },
    methods: {
        getLlibres(){
            getLibros().then((response) => {this.llibres = response})
            console.log("LLIBRES =>", this.llibres)
        }
    }
}
</script>