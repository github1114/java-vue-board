<template>
  <v-card class="elevation-12" width="500px">
    <v-toolbar color="primary" dark flat>
      <v-toolbar-title>Login form</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-card-text>
      <v-form>
        <v-text-field
          label="ID"
          prepend-icon="mdi-account"
          v-model="userId"
          type="text"
        ></v-text-field>
        <v-text-field
          id="password"
          label="Password"
          prepend-icon="mdi-lock"
          v-model="password"
          type="password"
        ></v-text-field>
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="primary" @click="onClickLoginButton">Login</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import {mapActions} from 'vuex'
export default {
  data() {
    return {
      userId: '',
      password: ''
    }
  },

  methods: {
    ...mapActions([
      'LOGIN'
    ]),
    onClickLoginButton() {

       this.LOGIN({userId: this.userId, password: this.password})
        .then(data => {
          console.log(data)
          this.$router.push('/board')
        })
        .catch(err => {
          this.error = err.data.error
          console.log(err);
        })
    },
  },
}
</script>
