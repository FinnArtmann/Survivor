package com.example.space_survivor


import com.soywiz.korau.sound.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korio.file.std.*
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.*
import com.soywiz.korma.geom.shape.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope



class Player : Container(){

    enum class State{
        IDLE,
        MOVING
    }

    private lateinit var idle: Image
    lateinit var moveSound: Sound
    lateinit var damageSound: Sound
    lateinit var state: State
    lateinit var soundChannel: SoundChannel
    lateinit var healthBar: HealthBar


    var moveSpeed = 600.0
    var moveX = 0.0
    var moveY = 0.0
    var health = 100.0
    var maxHealth = 100.0

    suspend fun loadPlayer(initialXPos: Double, initialYPos: Double) {
        position(initialXPos, initialYPos)
        state = State.IDLE

        damageSound = resourcesVfs["destroyed_stones.mp3"].readSound()
        moveSound = resourcesVfs["mystic.mp3"].readSound()
        idle = Image(
            resourcesVfs["Main Ship - Base - Full health.png"].readBitmap(),
            smoothing = false,
            anchorX = .5
        )

        healthBar = HealthBar(30.0).centerXOn(this).positionY(height + 20.0)


        //hitShape { circle { radius = 30.0 } }
        hitShape2d = Shape2d.Circle(48.0 /2 , 48.0 /2, 48.0)
        //var c = circle(48.0 /2).center()


        addChild(idle)
        addChild(healthBar)
    }


    fun isMoving() : Boolean{

        return moveX != 0.0 || moveY != 0.0;
    }
}