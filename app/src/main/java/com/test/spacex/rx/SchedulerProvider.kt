package com.test.spacex.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler

    fun queue(): Scheduler

}