<template>
    <div class="type--container">
        <div v-for="t in types" v-bind:key="t.id">
            <button class="button"> {{t.type}}</button>
        </div>
    </div>
</template>

<script>
import auth from "../auth";

export default {
    data() {
        return {
            types: []
        }
    },
    methods: {

    },
    created() {
      fetch( `${process.env.VUE_APP_REMOTE_API}/api/appliancetype`, {
          method: 'GET',
          headers: {
              Authorization: 'Bearer ' + auth.getToken(),
          },
          credentials: 'same-origin',
      })
        .then((response) => {
          return response.json();
        })
        .then((data) => {
            console.log(data);
          this.types = data;
        })
        .catch((err) => console.error(err));
    }
}
</script>

<style scoped>
#type--container {
    background-color: white;
    /* background: black; */
}
</style>