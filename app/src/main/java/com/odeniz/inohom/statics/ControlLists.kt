package com.odeniz.inohom.statics

import com.odeniz.inohom.R
import com.odeniz.inohom.model.GridItem
import kotlin.random.Random

object ControlLists {

    val items = listOf(
        GridItem(R.drawable.ic_favorite, R.string.favorites, id = 1),
        GridItem(R.drawable.ic_bulb_off, R.string.lighting, id = 2),
        GridItem(R.drawable.ic_blinds, R.string.curtain, id = 3),
        GridItem(R.drawable.ic_outlet, R.string.socket, id = 4),
        GridItem(R.drawable.ic_clapperboard, R.string.scenario, id = 5),
        GridItem(R.drawable.ic_remote_control, R.string.remote, id = 6),
        GridItem(R.drawable.ic_security_camera, R.string.camera, id = 7),
        GridItem(R.drawable.alarm, R.string.alarm, id = 8),
        GridItem(R.drawable.ic_intercom, R.string.intercom, id = 9),
        GridItem(R.drawable.ic_control, R.string.system, id = 10),
        GridItem(R.drawable.ic_heat, R.string.heating, id = 11),
        GridItem(R.drawable.ic_air_conditioner, R.string.air_conditioning, id = 12),
        GridItem(R.drawable.ic_sensor, R.string.sensor, id = 13),
        GridItem(R.drawable.ic_management, R.string.site_management, id = 14),
        GridItem(R.drawable.ic_bell, R.string.concierge, id = 15),
    )

    fun generateControlList(): List<GridItem> {
        val items = mutableListOf<GridItem>()
        val iconOn = R.drawable.ic_lightbulb
        val iconOff = R.drawable.ic_bulb_off
        val labels = listOf(
            R.string.management_map, R.string.serkan_led, R.string.management_spot,
            R.string.lamp, R.string.board_lighting_2, R.string.technical_lighting,
            R.string.rd_wc, R.string.meeting_lighting, R.string.entrance_lighting,
            R.string.kitchen_lighting, R.string.basement_storage_1,
            R.string.field_fault_lighting_1
        )
        for (i in labels.indices) {
            val isOn = Random.nextBoolean()
            val iconResId = if (isOn) iconOn else iconOff
            val labelResId = labels[i]
            items.add(GridItem(iconResId, labelResId, i, isOn))
        }

        return items
    }
}