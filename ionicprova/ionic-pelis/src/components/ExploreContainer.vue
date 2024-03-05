<template>
  <div id="container">
    <ion-searchbar :debounce="2000" @ionInput="handleInput($event)"></ion-searchbar>

    <ion-list>
      <ion-row>
        <ion-col v-for="resultat in results" :size="4">
          <ion-card>
            <img alt="foto" :src="resultat.Poster" style="height: 400px; width: auto;" />
            <ion-card-header>
              <ion-card-title>{{ resultat.Title }}</ion-card-title>
              <ion-card-subtitle>{{ resultat.Year }}</ion-card-subtitle>
            </ion-card-header>
          </ion-card>
        </ion-col>
      </ion-row>
    </ion-list>
  </div>
</template>

<script lang="ts">
import { IonSearchbarCustomEvent } from '@ionic/core';
import { IonSearchbar, SearchbarInputEventDetail, IonItem, IonList } from '@ionic/vue';
import { defineComponent, ref } from 'vue';

interface Movie {
  Poster: string;
  Title: string;
  Type: string;
  Year: string;
  // Other properties...
}
export default defineComponent({
  components: { IonSearchbar, IonList, IonItem },
  
  setup() {

    const results = ref<Movie[]>([]);

    return { results };
  },
  methods: {
    async handleInput(event: IonSearchbarCustomEvent<SearchbarInputEventDetail>) {
      const query = await fetch(`https://www.omdbapi.com/?s=$${event.target.value}&apikey=19f8a30e`);
      const resultat = await query.json();
      this.results = resultat.Search;
      console.log(this.results);
    },
  },
});
</script>

<style scoped>
#container {
  text-align: center;
  position: relative;
  left: 0;
  right: 0;
  top: 0;
}

#container strong {
  font-size: 20px;
  line-height: 26px;
}

#container p {
  font-size: 16px;
  line-height: 22px;
  color: #8c8c8c;
  margin: 0;
}

#container a {
  text-decoration: none;
}
</style>
