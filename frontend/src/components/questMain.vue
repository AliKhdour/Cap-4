<template>
  <div class="main-type--container">
        <div v-for="b in buttons" v-bind:key="b.id">
            <button @click="showApplianceTypes($event, b.id)" class="button">{{b.display}}</button>
            <div v-show="buttons[b.id].isShowing">
                <ul v-if="buttons[b.id].id == b.id">
                    <li v-for="item in buttons[b.id].types" v-bind:key="item.applianceTypeId" >
                        <a href="#" @click.prevent="captureItemType(item.type)">{{item.type}}</a>
                    </li>
                </ul>
            </div>
        </div>

  </div>
</template>

<script>
import auth from "../auth";

export default {
    name: 'questMain',
    props: 'item.type',
data() {
    return {
        buttons: [{
            id: 0,
            name: "Appliance",
            display: "+ Appliance",
            types: [],
            isShowing: false
        },
        {
            id: 1,
            name: "Mechanical Asset",
            display: "+ Mechanical Asset",
            types: [],
            isShowing: false
        },
        {
            id: 2,
            name: "Infastructure",
            display: "+ Infastructure Item",
            types: [],
            isShowing: false
        }
        ],
        userChoice: ""

    }
},
methods: {
    showApplianceTypes(event, buttonID){
        this.buttons[buttonID].isShowing = !this.buttons[buttonID].isShowing;
    },
    captureItemType(name) {
        this.$emit('item-type', name);
    }   
},
created() {
    let firstAPICall = fetch( `${process.env.VUE_APP_REMOTE_API}/api/appliancetype`, {
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',
    });
    
    let secondAPICall = fetch( `${process.env.VUE_APP_REMOTE_API}/api/mechanicalassettype`, {
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',
    });

    let thirdAPICall = fetch( `${process.env.VUE_APP_REMOTE_API}/api/infrastructuretype`, {
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + auth.getToken(),
        },
        credentials: 'same-origin',
    });

    Promise.all([firstAPICall, secondAPICall, thirdAPICall])
    .then(values => Promise.all(values.map(value => value.json())))
    .then(finalVals => {
        this.buttons[0].types = finalVals[0];
        this.buttons[1].types = finalVals[1];
        this.buttons[2].types = finalVals[2];
    });
}

}
</script>

<style scoped>

.button {
  border: none;
  color: var(--txt-prm-color);
  margin: 0 5%;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 24px;
  width: 90%;
}

.main-type--container {
  padding: 3%;
  background-color: var(--form-bg-color);
  border: 10px;
  margin: auto;
  width: 80%;
  max-width: 750px;
}

</style>