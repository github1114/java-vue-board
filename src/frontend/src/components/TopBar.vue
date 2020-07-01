<template>
    <v-card flat tile>
        <v-toolbar dense>

        <v-toolbar-title>Board App</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn v-if="userId != ''" icon @click="onClickLogout">
            <v-icon>mdi-logout</v-icon>
        </v-btn>
        </v-toolbar>
    </v-card>
</template>

<script>
import {mapActions, mapState} from 'vuex'
export default {
    data() {
        return {           
        }
    },

    computed : {
        ...mapState({
            userId : 'userId'
        })
    },

    methods: {
         ...mapActions([
            'LOGOUT'
        ]),
        onClickLogout() {
            this.LOGOUT()
            .then(data => {
            console.log(data)
            this.$router.push('/');
            })
            .catch(err => {
            this.error = err.data.error
            console.log(err);
            })
        }
    },
}
</script>