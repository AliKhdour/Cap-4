<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create an account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        There were problems registering this user.
      </div>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Email@address.com"
        v-model="user.username"
        required
        autofocus
      />
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm password"
        v-model="user.confirmPassword"
        required
      />
      <input
        type="text"
        id="firstname"
        class="form-control"
        placeholder="First name"
        v-model="userProfile.firstName"
        required
        autofocus
      />
      <input
        type="text"
        id="lastname"
        class="form-control"
        placeholder="Last name"
        v-model="userProfile.lastName"
        required
        autofocus
      />
      <input
        type="text"
        id="phone-number"
        class="form-control"
        placeholder="Phone number"
        v-model="userProfile.phoneNumber"
        required
        autofocus
      />
      <!-- <input
        name="sms"
        type="checkbox"
        id="sms"
        class="form-control"
        v-model="userProfile.notificationPreference"
        required
        autofocus
        checked
      /> -->
      <!-- <label style="color: white" for="sms">Receive Notifications by Text (Carrier Rates May Apply)</label> -->

      <router-link :to="{ name: 'login' }">
        Have an account?
      </router-link>
      <button  class="btn btn-lg btn-primary btn-block" type="submit">
        Register
      </button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      userProfile: {
        firstName: '',
        lastName: '',
        email: '',
        id: 0,
        notificationPreference: 'sms',
        phoneNumber: ''
      },
      state: {
        isSending: false
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: 'POST',
        headers: {
        Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            this.addProfile();
          } else {
            this.registrationErrors = true;
          }
        })
        .then((err) => console.error(err));
    },
    addProfile() {
      //Set profile data to fields in user data
      this.userProfile.email = this.user.username;
      /* if(document.getElementById("sms").checked){
        this.userProfile.notificationPreference = 'sms'
      }
      else{
        this.userProfile.notificationPreference = 'email'
      } */

      console.log(this.userProfile);
      console.log(`${process.env.VUE_APP_REMOTE_API}/newprofile`);
      
      fetch(`${process.env.VUE_APP_REMOTE_API}/newprofile`, {
        method: 'POST',
        headers: {
        Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.userProfile),
      })
      .then((response) => {
        if (response.ok) {
          this.$router.push({ path: '/login', query: { registration: 'success' } });
        } else {
          this.registrationErrors = true;
        }
      })
      .then((err) => console.error(err));
    }
  }
}
</script>

<style>


 #register {
    height: 100vh;
    width:100vw;
    background-color: var(--main-bg-color);
    z-index: 98;
    
    }
</style>
