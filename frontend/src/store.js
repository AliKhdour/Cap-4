import Vuex from "vuex";
import Vue from "vue";
import createPersistedState from 'vuex-persistedstate';
 
Vue.use(Vuex);
 
export default new Vuex.Store({
    state: {
      username: '',
      profile: {
        firstName: '',
        lastName: '',
        email: '',
        notificationPreference: '',
        id: 0,
      },
      homes: [],
      appliances: [],
      usertasks: [
        {
          userTaskId: 6,
          applianceId: 1,
          homeId: 1,
          taskId: 1,
          maintenanceDuration: 4,
          dateLastPerformed: '10/10/2019',
          taskDescription: ''}],
      tasks: [{
          taskid: 6,
          type: 'Water Heater',
          task: 'Drain and flush',
          description: 'Periodically flushing out the lime and other sediments in the water heater tank improves the heater’s efficiency and lifespan. In neglected water heaters, sediment can accumulate and calcify, making it difficult to remove. This can get so bad that the entire unit may need to be replaced. ',
          reason: 'Flushing your tank regularly, can prevent sediment from causing problems.',
          task_url: 'https://www.directenergy.com/blog/how-to-flush-a-water-heater/',
          task_interval: 12,
        }
      ]
    },
    plugins: [createPersistedState()],
    mutations: {
      setHome (state, homes) {
        for(let home of homes){
          console.log("Adding a home")
          console.log(home);
          state.homes.push(home);
        }
        
      },
      setTask (state, tasks){
        for (const task of tasks) {
          console.log("Adding a task")
          console.log(task);
          state.tasks.push(tasks);
        }
      },
      setUserTask (state, usertasks){
        for (const task of usertasks) {
          console.log("Adding a usertasks")
          console.log(task);
          state.usertasks.push(task);
        }
      },
      setUserName (state, username) {
        state.username = username;
      },
      setAppliance (state, appliances) {
        for(let appliance of appliances){
          console.log("Adding an appliance");
          state.appliances.push(appliance);
        }
      },
      setTaskId (state, tasks) {
        state.taskId.push(tasks);
      },
      setUserId (state, userId){
        state.userId = userId;
      }
    }
  })