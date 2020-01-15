<template>
<div id="questApp">

  <div v-for="q in questions" v-bind:key="q.id">
    <label :for="q.name">{{q.question}}</label>
    
    <div v-if="q.type === 'radio'">
      
      <div v-for="option in q.options" v-bind:key="option.value">
        <input type="radio" :name="q.name" v-model="q.value" :value="option.value"> {{option.text}}
      </div>

      <div v-for="opt in q.additional_questions" v-bind:key="opt.id">
        <div v-if="opt.type === 'radio' && q.value === opt.condition">
         <label :for="opt.name">{{opt.question}}</label>
          <div v-for="option in opt.options" v-bind:key="option.value">
            <input type="radio" :name="opt.name" v-model="opt.value"
                   :value="option.value"/> {{option.text}} 
          </div>
        </div>
      
      </div>
    
    </div>

  </div>
</div>
</template>

<script>
export default {
  name: 'questApp',    
data() {
    return {
    questions: [{
        id: 1,
        value: null,
        name: "Oven",
        question: "Do you own an Oven?",
        type: "radio",
        options: [{
          text: "Yes",
          value: "0"
        }, {
          text: "No",
          value: "1"
        }],
        additional_questions: [
        {
            condition: "0",
                id: 99,
            value: null,
            name: "Model Number", 
            question: "Do you know the model number?",
            type: "radio",
            options: [{
                text: "Yes",
                value: "0"
            }, {
                text: "No",
                value: "1"
                }]
          }
        ],
        
      },
      {
        id: 2,
        value: null,
        name: "Refrigerator",
        question: "Do you own an Refrigerator?",
        type: "radio",
        options: [{
          text: "Yes",
          value: "0"
        }, {
          text: "No",
          value: "1"
        }],
        additional_questions: [
        {
            condition: "0",
                id: 98,
            value: null,
            name: "Model Number", 
            question: "Do you know the model number?",
            type: "radio",
            options: [{
                text: "Yes",
                value: "0"
            }, {
                text: "No",
                value: "1"
                }]
          }
        ],
      }
    ]
    }
  },
 
  methods: {

  }
}
</script>

<style scoped>
  
</style>