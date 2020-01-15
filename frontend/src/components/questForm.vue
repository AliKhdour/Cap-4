<template>
  <div class="main-form--container" >
      
      <h2 class="main-form--header">{{type}}</h2>
      
      <form class="main-form--form" >
          <h2>Model #:</h2>
          <input id="modelNumber" type="text">
          <h2>Last filter change:</h2>
          <input id="lastFilterChange" type="date">
          <h2>Age of machine:</h2>
          <input id="assetAge" type="date">
          <input @click.prevent="submitForm()" type="button" value="Submit">
      </form>


  </div>
</template>

<script>
import auth from "../auth";

export default {
  name: 'questForm',
  props: {
    type: String,
    
  },
  data(){
    return {
      formValues: {
        modelNumber: "",
        age: "",
        type: "",
        homeId: 1,
        applianceId: 0,
      }
    }
  },
  methods: {
    submitForm(){
      this.formValues.modelNumber = document.getElementById("modelNumber").value;
      this.formValues.age = document.getElementById("assetAge").value;
      this.formValues.type = this.type;
      

      fetch(`${process.env.VUE_APP_REMOTE_API}/api/newappliance`, 
        { 
          method: 'POST',
          headers: {
              Authorization: 'Bearer ' + auth.getToken(),
              Accept: 'application/json',
              'Content-Type': 'application/json'
          },
          credentials: 'same-origin',
          body: JSON.stringify(this.formValues),
        })
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          this.formValues = data;
        })
        .catch((err) => console.error(err));
    }
  }
}
</script>

<style scoped>


.main-form--header{
  background: var(--profile-bg-color);
  color: var(--txt-sec-color)
}

.main-form--container {
  width:60vw;
  min-width: 50%;
  height:100%;
  padding-top: 3%;
  background-color: var(--form-bg-color);
  border: 10px;
  margin: 20px auto;
  width: 80%;
  max-width: 750px;
     
}
.main-form--form {
  width:60vw;
  min-width: 50%;
  height: 100%;
  padding:20px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  position: relative;
  top:25%;
  z-index: 2;
  background-color: var(--form-bg-color-app);
  color: var(--txt-sec-color);
  box-shadow: var(--std-box-shadow);
}

main-form--header{
  color: var(--txt-prm-color);
}


</style>