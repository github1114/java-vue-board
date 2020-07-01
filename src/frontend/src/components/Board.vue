<template>
    <div style="width:1000px">
        <v-data-table :headers="headers" :items="list" class="elevation-4">
            <template v-slot:top>
                <v-toolbar flat color="white">
                    <v-toolbar-title>Board</v-toolbar-title>
                    <v-divider class="mx-4" inset vertical></v-divider>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" @click="onClickNewButton">추가</v-btn>
                </v-toolbar>
            </template>
            <template v-slot:item.actions="{ item }">
            <v-icon
                v-if="item.userId == userId"
                small
                class="mr-2"
                @click="onClickEditButton(item)"
            >
                mdi-pencil
            </v-icon>
            <v-icon
                v-if="item.userId == userId"
                small
                @click="onClickDeleteButton(item)"
            >
                mdi-delete
            </v-icon>
            </template>
            <template v-slot:no-data>
                <div>
                    <b>데이터가 없습니다</b>
                </div>
            </template>
        </v-data-table>
        <v-dialog persistent v-model="dialog" max-width="600px">
            <v-card>
                <v-card-title class="headline">팝업</v-card-title>

                <v-card-text>
                    <v-row>
                        <v-col v-if="!isCreate" cols="12">
                            <v-text-field
                                label="번호"
                                readonly
                                filled
                                v-model="editedItem.boardId"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field
                                label="제목"
                                outlined
                                counter
                                v-model="editedItem.title"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-textarea
                                outlined
                                name="input-7-4"
                                label="내용"
                                counter
                                v-model="editedItem.context"
                            ></v-textarea>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field
                                label="작성자"
                                filled
                                readonly
                                v-model="editedItem.userId"
                            ></v-text-field>
                        </v-col>
                    </v-row>
                </v-card-text>
                
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="onClickActionButton">Action</v-btn>
                    <v-btn color="green darken-1" text @click="onClickCloseButton">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
        <v-alert v-model="success" type="success">
            {{msg}}
        </v-alert>
        <v-alert v-model="error" type="error">
            {{msg}}
        </v-alert>
    </div>
</template>

<script>

import {mapState} from 'vuex';

export default {
    data() {
        return {
            success: false,
            error: false,
            msg: '',
            headers: [
                {text: 'Actions', value: 'actions'},
                {text: '게시글 번호', value: 'boardId'},
                {text: '제목', value: 'title'},
                {text: '내용', value: 'context'},
                {text: '작성자', value: 'userId'},
            ],
            list: [],
            dialog: false,
            isCreate: false,
            editedItem: {
                boardId: '',
                title: '',
                context: '',
                userId: ''
            },
            defaultItem: {
                boardId: '',
                title: '',
                context: '',
                userId: ''
            },
            apiUrl: 'http://localhost:8000/api/v1/boards'
        }
    },

    computed : {
        ...mapState({
            userId : 'userId'
        })
    },

    methods: {
        onClickCloseButton() {
            Object.assign(this.editedItem, this.defaultItem);
            this.dialog = false;
        },

        onClickNewButton() {
            this.dialog = true;
            this.isCreate = true;
            Object.assign(this.editedItem, this.defaultItem);
            this.editedItem.userId = this.userId;
        },

        onClickEditButton(item) {
            this.dialog = true;
            this.isCreate = false;
            Object.assign(this.editedItem, item);
        },

        async onClickDeleteButton(item) {
            console.log('delete item: ', item)
            try {
                let result = await this.axios.delete(this.apiUrl, {
                    params: {
                        boardId: item.boardId
                    }
                });

                if(result.status === 200) {
                    this.getBoards();
                }  

            } catch (error) {
                console.log(error);
            }
        },

        async onClickActionButton() {
            console.log('item: ', this.editedItem)
            let result = undefined;

            try {
                if(this.isCreate) {
                result = await this.axios.post(this.apiUrl, this.editedItem);
                console.log('추가 결과: ', result)
                
                } else {
                    result = await this.axios.patch(this.apiUrl, this.editedItem);
                    console.log('수정 결과: ', result)
                }

                if(result.status === 200) {
                    this.getBoards();
                    this.dialog = false;
                    this.success = true;
                    this.msg = this.isCreate ? '글 작성 성공' : '글 수정 성공';
                    setTimeout(() => {
                        this.success = false;
                    }, 3000);
                } else {
                    this.error = true;
                    this.msg = this.isCreate ? '글 작성 실패' : '글 수정 실패';
                    setTimeout(() => {
                        this.error = false;
                    }, 3000);
                }          
            } catch (error) {
                this.error = true;
                this.msg = `요청 실패. 에러코드: ${error}`;
                setTimeout(() => {
                    this.error = false;
                }, 3000);
            }   
        },

        async getBoards(){
            try {
                let result = await this.axios.get(this.apiUrl,{
                    params: { page: 0, size: 20 }
                });
                this.list = result.data;
            } catch (error) {
                console.log(error);
            }
        },
    },

    created() {
        this.getBoards();
        console.log('userId: ', this.userId)
    },
}
</script>