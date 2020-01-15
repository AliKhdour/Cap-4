<template>
  <div class="home-app--container"> 
    <tbody class="home-app--desc-container" v-for="app in appliances" v-bind:key="app.applianceId"> 
      <h4 class="home-app--desc-header">
        <td class="home-app--desc">{{ app.type }}</td>
      </h4>
      <maint-task v-bind:applianceId="app.applianceId"/>
    </tbody>
  </div>
</template>

<script>
  import maintTask from "../components/maintTask";

  export default {
    name: 'homeAppliance',
    props: {
      homeId: Number
    },
    data () {
      return {
        appliances: [
        ]
      }
    
    },
    mounted(){
      
      this.appliances = this.$store.state.appliances.filter(appliance => appliance.homeId == this.homeId)
    },
    components: {
      maintTask
    }
  }

</script>

<style scoped>

  .home-app--container{
    display: flex;
    flex-direction: column;
  }

  .home-app--desc-container{
    max-width: 800px;
    margin: 20px 2%;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    background-color: rgba(255, 255, 255, .5);
  }

  .home-app--desc-header{
    
    color: var(--txt-sec-color);
    width: 100%;
    min-width: 200px;
    margin: 0;
    padding: 10px;
    background: var(--profile-bg-color);
  
  }

  .home-app--desc { 
    color: var(--txt-prm-color);
    font-size: 1.5rem;

  }

  @media screen and (min-width: 1000px) {
    .home-app--desc-container{
      max-width: 700px;
    }

    .home-app--container{
      flex-direction: row;
    }

    .home-app--desc-container{
    min-width: 400px;
    margin: 20px auto;
    width: 40%;
    
  }





  }

</style>
