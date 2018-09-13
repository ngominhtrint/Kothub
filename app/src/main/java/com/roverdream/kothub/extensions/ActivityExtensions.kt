package com.roverdream.kothub.extensions

import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.enableToolbarBackButton() {
    delegate.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}