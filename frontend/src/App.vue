<template>
  <div id="app">
    <img id="background-image" src="./assets/backgroundimageop2.jpg" alt="">
    <div id="nav">
      <div class="topnav">
        <div class="home-logo">
          <router-link to="/">
            <img class="nav-logo" src="./assets/HHTransparent-white.png" alt="Home Helper Logo">
          </router-link>
        </div>
        <div class="center">
        <router-link to="/profile">Profile</router-link>
        <div class="topnav-right">
          <router-link v-show="!state.isAuth"  @click="logout" to="/">
            Logout
          </router-link>
          <router-link v-show="state.isAuth" to="/login">Login</router-link>
          <router-link v-show="state.isAuth" to="/register">Register</router-link>
        </div>
        </div>
      </div>
    </div>
    
    <router-view class="view-container"/>
  </div>
</template>
<script>
export default {
  data(){
    return {
      state: {
        isAuth: true,
      }
    };
  },
  methods: {
    logout(){
      this.$auth.logout();
      this.state.isAuth = false;
    }
  }
}
</script>




<style>

.view-container{
  padding-top: 100px;
}

#nav {
  position:fixed;
  top: 0;
  z-index:99;
  width: 100%;
	height: 80px; 
	overflow: hidden;
}


/* Add a black background color to the top navigation */
.topnav {
  position: relative;
  background-color: var(--main-bg-color);
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  
  float: left;
  color: var(--txt-prm-color);
  text-align: center;
  padding: 14px 16px 14px 16px;
  text-decoration: none;
  font-size: 17px;
  outline: 0;
  padding: 5px 12px;
  display: block;
  font-weight: bold;
  text-shadow: 1px 1px #1f272b;
  border: 1px solid #1c252b;
  border-radius: 3px;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  background: #232B30; /* old browsers */
  background: -moz-linear-gradient(top, #3D4850 3%, #313d45 4%, #232B30 100%); /* firefox */
  background: -webkit-gradient(linear, left top, left bottom, color-stop(3%,#3D4850), color-stop(4%,#313d45), color-stop(100%,#232B30)); /* webkit */
  box-shadow: 1px 1px 1px rgba(0,0,0,0.2); 
}

.home-logo a {
  
  float: left;
  color: none;
  text-align: center;
  padding: 14px 16px 14px 16px;
  text-decoration: none;
  font-size: 17px;
  outline: 0;
  padding: 5px 12px;
  display: block;
  font-weight: bold;
  text-shadow: none;
  border: none;
  border-radius: 3px;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  background: none; /* old browsers */
  box-shadow: none; 
}

/* Change the color of links on hover */
.topnav a:hover {
  color: #fff;
  background: #4C5A64; /* old browsers */
  background: -moz-linear-gradient(top, #4C5A64 3%, #404F5A 4%, #2E3940 100%); /* firefox */
  background: -webkit-gradient(linear, left top, left bottom, color-stop(3%,#4C5A64), color-stop(4%,#404F5A), color-stop(100%,#2E3940)); /* webkit */
}

/* Add a color to the active/current link */
.topnav a.active {
  background-position: 0 top;
  position: relative;
  top: 1px;
  color: var(--txt-prm-color);
  padding: 6px 12px 4px;
  background: #20282D; /* old browsers */
  background: -moz-linear-gradient(top, #20282D 3%, #252E34 51%, #222A30 100%); /* firefox */
  background: -webkit-gradient(linear, left top, left bottom, color-stop(3%,#20282D), color-stop(51%,#252E34), color-stop(100%,#222A30)); /* webkit */
  box-shadow: 1px 1px 1px rgba(255,255,255,0.1);
}

/* Centered section inside the top navigation */
.topnav-centered a {
  float: none;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

/* Right-aligned section inside the top navigation */
.topnav-right {
  float: right;
}

.center{
  padding: 15px;
}

#background-image{
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  opacity: 40%;
  position: fixed;
  z-index: -99;
}

</style>
