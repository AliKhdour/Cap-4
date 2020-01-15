<template>
  <div class="home">
    <div class="mission-statement">

      <h1 class="home-info--mission-header">Welcome to HomeHelper! </h1>

      <h4 class="home-info--mission-statement"> We help you keep track of maintenance tasks around your home. 
Owning a home can be a complicated and confusing process. It’s easy to forget the little things that help to maintain, preserve, and prolong the life of a home. </h4>

      <ol class="home-info--mission-list">
        Doing simple tasks can significantly increase the lifespan of appliances.
        <li> Changing filters regularly 
          <img src="../assets/filters.jpeg" alt="" id="filter">
        </li>

        <li>Cleaning components</li>

        <li>Checking the condition of parts</li> 
        
        <li>Making adjustments based on weather conditions</li>
      </ol>
      
      <p class="home-info--mission-txt" id="questionnaire">      Using a short questionnaire, HomeHelper builds a profile of your home to track the history of your home's essential items.
      </p>
      
      <p class="home-info--mission-txt" id="oneclick">
      Completing a task and setting a new reminder takes a single click. HomeHelper logs the date and sets a new completion date.
      </p>
      
      <div class="screencap-of-button" id="oneclickimg">Screencaps of task completion</div>

      <p class="home-info--mission-txt" id="notifications">
      HomeHelper provides options for direct notifications on upcoming maintenance tasks. 
      </p>
      <div class="screencap-of-button" id="notificationimg">Notifications</div>
      <p class="home-info--mission-txt" id="savings">
      Our goal is to allow the homeowner to save time, money, and focus on the things that matter.
      </p>
      <div class="screencap-of-button" id="savingsimg">savingimg</div>
    </div>
    <div class="home-info--card">
      <img src="../assets/croppedHomeCareGraphic.png" alt="infograph of routine maintenance" class="home-info--card-img">
    </div>
  </div>
</template>

<script>
export default {
  name: 'home'
}
</script>


<style scoped>



.home{
  width: 100vw;
  padding: 60px 0 0 0;
  display:grid;
  grid-column-gap: 10px;
  grid-template-rows: auto;
  grid-template-columns: 1fr minmax(250px, 800px) minmax(250px, 400px) 1fr;
  grid-template-areas: ".content info.";
  justify-content: space-around;
}


.mission-statement {
  background-color: rgba(255, 255, 255, 0.5);
  color:white;
  grid-area: content;
  display: grid;
  grid-row-gap: 10px;
  grid-template-rows: auto;
  grid-template-columns: repeat(5, 100px) ;
  grid-template-areas:
  ".            header       header       header        header"
  "missionstate missionstate missionstate missionstate  ."
  "missionlist missionlist missionlist missionlist  missionlist"
  "missionquote missionquote missionquote missionquote  missionquote"
  "oneclick oneclick oneclick oneclick  ."
  ". oneclickimg oneclickimg oneclickimg .  "
  " notifications notifications notifications notifications . "
  " notificationimg notificationimg notificationimg . . "
  ". savings savings savings savings  "
  ". savingsimg savingsimg savingsimg .  "
  ;
}

#savingsimg{
  grid-area: savingsimg;
}

#savings{
  grid-area: savings;
}
#notificationimg{
  grid-area: notificationimg;
}

#notifications{
  grid-area: notifications;
  }

#oneclickimg{
  grid-area: oneclickimg;
}
#questionnaire {
  grid-area: missionquote;
}

#oneclick{
  grid-area: oneclick;
}

.oneclickimg{
  grid-area: screencap;
}

.home-info--mission-statement{
  grid-area: missionstate;
}

.home-info--mission-list{
  grid-area: missionlist;
}

.home-info--mission-header{
  grid-area: header;
}

.home-info--mission-txt{
  color:white;
  font-size: 1.5rem;
 
  background-color: rgba(255, 255, 255, 0.5);
}
.home-info--mission-txt{
  
  color:white;
  font-size: 1.5rem;
  background-color: rgba(255, 255, 255, 0.5);;
}
.screencap-of-button{
 
  width: 600px;
  height: 300px;
  background-color: rgba(255, 255, 255, 0.5);;
}



.home-info--card{
  grid-area: info;
  /* position: absolute; */
  z-index: 2;
  /* left:10%;
  top:25%; */
  box-shadow: var(--std-box-shadow);
}

.home-info--card img{
  width: 100%;
}

</style>