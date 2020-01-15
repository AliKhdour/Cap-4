<template>
  <div class="maint-task--container">
    <tbody class="maint-task--container-desc" v-for="task in maintainTasks" v-bind:key="task.taskid" >
       <!-- | filterById -->
      <!-- <h6 class="main-task--header-complete">Completed:</h6> -->
      <checkmark-icon class="maint-task--complete"></checkmark-icon>
      <h6 class="main-task--header-task">Task:</h6>
      <td class="maint-app--desc">
        {{ task.task }} 
      </td>
      <td class="maint-app--desc"> 
        <span class="main-task--header-due"> Due: </span>
        {{ task.dateLastPerformed }} 
      </td> 
      <!-- use this date to determine state. to save the duration to param -->
      <td v-show="!state.isAlert" class="maint-app--desc">
        <a href="#">A link: {{ task.website }}</a>
      </td>

      <b-button v-b-toggle.collapse-a.collapse-b class="maint-app--info">
        <info-icon id="info-icon"></info-icon>
      </b-button>
     
      <!-- Elements to collapse -->
      <b-collapse id="collapse-a" class="maint-app--descipt">
        <b-card class="maint-app--descipt">{{task.taskDescription}} <a href="#">Guide: {{ task.website }}</a></b-card>
      </b-collapse>
      
      
    </tbody>
  </div>
</template>



<script>
import CheckmarkIcon from "vue-material-design-icons/CheckboxMarkedCircleOutline.vue";
import InfoIcon from "vue-material-design-icons/InformationOutline.vue";
import 'bootstrap/dist/css/bootstrap.css';


export default {
  name: 'maintTask',
  components: {
    CheckmarkIcon,
    InfoIcon
  },
  mounted(){
    this.maintainTasks.push( this.$store.state.tasks);
  }, 
  
  data () {
      return {
        state: {
          isAlert:true,
        },
        maintainTasks: [
          
        ]
      }
    
    },

    methods:{
      //Need to determine if this functions and if so how to deploy per task
       performedDate(){
          let lastPerf = new Date(this.maintainTasks.dateLastPerformed);

          const maintDur = this.appliance.maintDur * 7;

          lastPerf.setDate(lastPerf.getDate() + maintDur);
          this.maintainTasks.recPerformDate = lastPerf
       }
      }
    }

</script>
 
<style scoped>

 

 .maint-task--container-desc{
    padding: 10px;
    font-size: 1.2rem;
    margin: 10px 4%;
    box-shadow: var(--std-box-shadow);
    background-color: var(--maint-bg-color);
    max-width: 350px;
    min-width:250px;

    display:  grid;
    grid-template-columns: 1fr, 2fr, 1fr;
    grid-template-rows: auto;
    grid-template-areas: 
      ".        task        . "
      "icon     description info"
      "icon     due         info"
      ". descrip descrip";

  } 

  .maint-app--descipt{
    grid-area: descrip;
  }

  .main-task--header-task {
    grid-area: task;
    border-bottom: 1px black;
  }

  .maint-task--complete{
    grid-area: icon;
    margin: auto 10px;
  }

  .maint-app--desc:first-of-type {
    grid-area: description;
  }

  .maint-app--desc {
    grid-area: due;
  }

  .maint-app--info {
    grid-area: info;
    background-color: rgba(0,0,0,0);
    border-color: rgba(0,0,0,0);
  }

  .maint-app--info:not(:disabled):not(.disabled):active{
    outline: 0;
    background-color: rgba(0,0,0,0);
    border-color: rgba(0,0,0,0);
    box-shadow: none;
  }

  .maint-app--info:focus {
    box-shadow: none;
    background-color: rgba(0,0,0,0);
    border-color: rgba(0,0,0,0);
  }

  #info-icon {
    color: var(--icon-color);
  }

  .main-task--header-due{
    font-size: .75rem;
    padding: auto 0;
  }

</style>