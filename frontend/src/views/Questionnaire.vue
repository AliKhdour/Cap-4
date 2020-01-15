<template>
    <div class="questionnaire">
      <div v-show="!isSubmitted" class="new-home--container">
        <quest-new-home @is-submitted="onClick" v-show="!isSubmitted" class="new-home"> </quest-new-home>
      </div>
        <quest-main v-on:item-type="passTypeData"> </quest-main>
        <quest-form v-bind:type="type" > </quest-form>
    </div>
</template>

<script>
import questNewHome from "../components/questNewHome";
import questMain from "../components/questMain";
import questForm from "../components/questForm";


export default {
  name: 'questionnaire',
  data() {
    return {
      type: '',
      isSubmitted: false,
      isFormShowing: false
    }
  },
  components: {
        questNewHome,
        questMain,
        questForm
  }, 
  methods: {
    onClick (value){
      this.isSubmitted = value;
    },
    passTypeData(query) {
      this.type = query;
    }
  }
}
</script>

<style scoped>

.new-home--container{
  background-color: var(--main-bg-color);
  height: 120vh;
  width: 100%;
  z-index: 98;
  top: -100px;
  position:absolute;

}

.new-home{
    z-index: 99;
    top: 20%;
    position: sticky;
    left: 20%;
}
</style>