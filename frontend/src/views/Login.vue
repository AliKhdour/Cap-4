<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid email or password!
      </div>
      <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
        Thank you for registering! Please sign in.
      </div>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Email"
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
      <div class="form-signin--new">
        <router-link :to="{ name: 'register' }">Need an account?</router-link>
      </div>
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'login',
  components: {},
  
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      state: {
        isSending: false
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          console.log(`${token}`);
          
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
            console.log(this.user.username)
            this.$store.commit("setUserName", this.user.username);
            console.log("store username: " + this.$store.state.username)
            this.$router.push('/');
          }
        })
        .catch((err) => console.error(err));
    }   
  },
};
</script>

<style scoped>

  #login {
    height: 100vh;
    width:100vw;
    background-color: var(--main-bg-color);
    z-index: 98;
    }

  .form-signin   {
    margin: 0 auto;
  }


/*----------- sign button ---------*/

.form-signin > button  {
  width: 80%;
}


/*----------- register  button ---------*/
.form-signin--new > a {
  margin: 20px;
  width: 200px;
	background-color:var(--btn-regis-color);
  line-height: 50px;
  height: 50px;
}
.form-signin--new > a:hover {
	background-color:var(--btn-regis-hover-color);
}
.form-signin--new > a:active {
	position:relative;
	top:2px;
}


</style>
