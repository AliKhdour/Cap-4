<template>
  <div class="user-profile--container">
      <header class="user-profile--header">
        <h3 class="user-profile--name">
          Welcome, {{ user.firstName }}!
        </h3>
      </header>
      <!-- <h4 v-show="!state.isAlert" class="user-profile--notifications">
        You have {{user.alerts}} notifications.
      </h4> -->
      <user-home/>
  </div>
</template>

<script>
import userHome from "../components/userHome";
import auth from "../auth";

export default {
    name: 'userProfile',
    components: {
      userHome
    },
    data() {
      return {
        user: {
            firstName: '',
            lastName: '',
            username: '',
            role: 'user'
          },
        state:{
          isAlert: true,
        }
      };
    },
    mounted() {
      this.getUserProfile();
      this.getUserHomes();
  },
  methods: {
    getUserProfile(){
      let username = this.$store.state.username;
      console.log("sending to API: " + username)
      // This needs a GET request first then upon failure send a POST else proceed
      let urlPath = process.env.VUE_APP_REMOTE_API+"/api/profile?username="+ username
      console.log(urlPath)

      fetch(urlPath,{
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',})
        .then((response) => {
          if (response.ok) {
            console.log(response.json().then(data => {
              this.user.firstName = data.firstName;
              this.user.lastName = data.lastName;
              this.user.username = data.email;
            }
            )
            );
            //return response.text();
          } else {
            console.log(`Account does not exist`);
          }
        });
    },
    getUserHomes(){
      this.$store.state.homes = [];
      let username = this.$store.state.username;
      // This needs a GET request first then upon failure send a POST else proceed
      let urlPath = process.env.VUE_APP_REMOTE_API+"/api/homes?username="+ username

      fetch(urlPath,{
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',})
        .then((response) => {
          if (response.ok) {
            console.log(response.json().then(data => {
              this.$store.commit("setHome", data);

              for(let aHome of this.$store.state.homes){
                console.log("Checking for appliances for homeId.. " + aHome.homeId);
                this.getUserAppliances(aHome.homeId);
              }
            }
            )
            );
            // return response.text();
          } else {
            console.log(`Account does not exist`);
          }
        });
    },
    getUserAppliances(homeId){
      this.$store.state.appliances = [];
      // This needs a GET request first then upon failure send a POST else proceed
      let urlPath = process.env.VUE_APP_REMOTE_API+"/api/appliances?homeId="+ homeId

      fetch(urlPath,{
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',})
        .then((response) => {
          if (response.ok) {
            console.log(response.json().then(data => {
              console.log(data);
              this.$store.commit("setAppliance", data);
            }
            )
            );
            // return response.text();
          } else {
            console.log(`Appliances do not exist`);
          }
        });
    },
  }
}
</script>

<style scoped>

  .user-profile--container {
    padding : 0 0 1% 0;
    
  }

  .user-profile--header {
    
    color: var(--txt-prm-color);
    background: var(--profile-bg-color);
    opacity: 100%;
    margin: 1%;
    padding: 1%;
  }


  .user-profile--notifications {
    text-align: center;
    color: var(--txt-prm-color);
    font-size: 2rem;
  }



</style>