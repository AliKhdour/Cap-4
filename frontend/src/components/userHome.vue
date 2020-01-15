<template>
  <div class="home-container">
    <div v-for="home in homes" :key="home.homeId" class="home-item--container">
      <alert-icon class="home-item--alert-icon">  </alert-icon>
      <div class="home-header--image-container">
        <img src="../assets/HomeCard.jpeg" alt="image of users house" class="home-header--image">
      </div>

      <settings-icon class="home-item--setting-icon"> 
      </settings-icon>

      <h1 class="home-header--description">
        Home: {{ home.address}} 
      </h1>
  
      <home-appliance v-bind:homeId="home.homeId"></home-appliance> 

      <home-mech-asset></home-mech-asset>

    </div>
  </div>
</template>

<script>
import homeAppliance from "../components/homeAppliance";
import homeMechAsset from "../components/homeMechAsset";
import SettingsIcon from "vue-material-design-icons/Settings.vue";
import AlertIcon from "vue-material-design-icons/Alert.vue";
// import auth from "../auth";

export default {
  name: 'userHome',

  created(){
    this.homes = this.$store.state.homes
  },
  data() {
    return {
      homes: [
        ],
      deleteHomeId: null,
    };
  },
  components: {
    homeAppliance,
    homeMechAsset,
    SettingsIcon,
    AlertIcon

  },
  methods: {

    
  },
}
</script>

<style scoped>
  
  .home-item--container{
    margin:1% 5%; 
    background-color:var(--form-bg-color);
    background: url(../assets/dirty.png), rgba(255,255,255,.8);
    box-shadow: var(--std-box-shadow);
    min-width: 324px;
   
  } 

  .home-item--setting-icon{
    color: white;
    position: relative;
    right: -290px;
    top: -200px;
    z-index: 99;
  }

  .home-item--alert-icon{
    color: #FBC02D;
    position: relative;
    left: 0px;
    top: 40px;
    /* z-index: 2; */
  }

  .home-header--description {
    color:var(--txt-prm-color);
    background: var(--profile-bg-color);
    font-size: 1rem;
    position: relative;
    top:-72px;
    width: 280px;
    height: 50px;

    margin:0;
  }

  .home-header--image-container {
    display: flex;
    flex-direction: row;
    opacity: 50%;
    overflow: hidden;
    min-width: 324px;
    

  }

  .home-header--image{
    width: 324px;
  }


</style>