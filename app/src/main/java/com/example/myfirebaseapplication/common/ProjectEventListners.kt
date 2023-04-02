package com.example.myfirebaseapplication.common

interface ProjectEventListners {
    interface LoginEvents{
        fun login()
        fun navToSignUp()
    }
    interface SignUpEvents{
        fun signUp()
        fun takeImage()
    }

    interface DetailPageEvents{
        fun backBtnPress()
    }
    interface OnRecyclerViewClicked{
        fun onClick()
    }
}