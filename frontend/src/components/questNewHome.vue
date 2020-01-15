<template>
  <form class="questionnaire-home--form">
      <h1 class="questionnaire-home--header"> 
        Welcome, {{this.$store.state.profile.firstName}}! Please add a home.
      </h1>
      <div class="form-group">
        <input type="street" 
                class="form-control" 
                id="autocomplete" 
                placeholder="Street Address">
        
        <input type="city" 
                class="form-control" 
                id="inputCity" 
                placeholder="City">
        
        <input type="state" 
                class="form-control" 
                id="inputState" 
                placeholder="State">
        
        <input type="zip" 
                class="form-control" 
                id="inputZip" 
                placeholder="Zip Code">
        
        <input type="occupants" 
                class="form-control" 
                id="inputOccupants" 
                placeholder="Number Of Occupants">
        
        <input type="phone" 
                class="form-control" 
                id="inputPhone" 
                placeholder="Phone Number">
        <input @click.prevent="submitHome()" type="submit" value="Add Home to profile">
        </div>
    </form>
</template>

<script>
import auth from "../auth";

export default {
  name: 'questNewHome',
  props: {
    type: String
  },
  
  data(){
    return {
      isSubmitted: true,
      formValues: {
        homeId: 1,
        userId: 1,
        address: '',
        addressLineTwo: 'Apt. 1',
        city: '',
        state: '',
        zip: '',
        occupants: Number,
      }
    }
  },
  methods: {
    submitHome(){
      this.submitForm();
      this.showForm(this.isSubmitted);
    },
    showForm(isSubmitted){
      this.$emit('is-submitted', isSubmitted);
    },
    submitForm(){
    
    this.formValues.address = document.getElementById("autocomplete").value;
    this.formValues.city = document.getElementById("inputCity").value;
    this.formValues.state = document.getElementById("inputState").value;
    this.formValues.zip = document.getElementById("inputZip").value;
    this.formValues.occupants = document.getElementById("inputOccupants").value;
    console.log(JSON.stringify(this.formValues));
    fetch(`${process.env.VUE_APP_REMOTE_API}/api/newhome`, 
      { 
        method: 'POST',
        headers: {
            Authorization: 'Bearer ' + auth.getToken(),
            Accept: 'application/json',
            'Content-Type': 'application/json'
        },
        credentials: 'same-origin',
        body: JSON.stringify(this.formValues),
      })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        this.formValues = data;
      })
      .catch((err) => console.error(err));
    }
  }
}

var placeSearch, autocomplete;

// List all address components (corresponds to form field IDs and Google address object)
var componentForm = {
  autocomplete: ['street_number', 'route'],
  inputCity: 'locality',
  inputState: 'administrative_area_level_1',
  inputZip: 'postal_code',
  inputOccupants: 'occupants',
  inputPhone: 'phone'
};

// Create autocomplete object based on the autocomplete ("street") field
// Location type restricted to geocode
function initAutocomplete() {
  autocomplete = new google.maps.places.Autocomplete(
      /** @type {!HTMLInputElement} */ 
      (document.getElementById('autocomplete')),
      {type: ['geocode']});

// Call fillInAddress when user selects an address from dropdown
  autocomplete.addListener('place_changed', fillInAddress);
}

// Fill fields with values from Google Maps autocomplete object
function fillInAddress() {

// Get place data from autocomplete object
  var place = autocomplete.getPlace();
  console.log(place);
  
// Enable each field, then fill them with the corresponding value from the place object
  for (var component in componentForm) {
    document.getElementById(component).disabled = false;
    document.getElementById(component).value = search(componentForm[component], place.address_components);
  }
  // Fill the autocomplete field with values from the place object
  // If a street number is not found, set the field to route only.
  if (search("street_number", place.address_components) != "") {
    document.getElementById("autocomplete").value = search("street_number", place.address_components) + " ";
  }
  document.getElementById("autocomplete").value += search("route", place.address_components);
  
  // Search the passed object for a specified address component/type and return the short_name value of the matched component/type
  // If requested type does not exist in the placeObject, return an empty string
  function search(type, placeObject) {
    for (var i = 0; i < placeObject.length; i++) {
      if (placeObject[i].types[0] === type) {
        return placeObject[i].short_name;
      } else if (i === placeObject.length - 1) {
        return "";
      }
    }
  }
}


</script>

<style scoped>

.questionnaire-home--form {
  min-width: 350px;
  max-width: 700px;
  padding: 5%;
  width: 60vw;
  height: 65%;
  display:flex;
  flex-direction: column;
  background-color: var(--form-bg-color);
  color: var(--txt-prm-color);
}


input {
 margin: 15px auto;
}

</style>