package com.vinhdn.order.manager.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonView
import com.vinhdn.order.manager.entity.view.UserViews
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class User : Serializable {
    @JsonView(UserViews.Member::class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    var id: Long? = null

    @JsonView(UserViews.Member::class)
    @Column(name = "username")
    var userName: String? = null

    @JsonIgnore
    @Column(name = "password")
    var password: String? = null

    @JsonView(UserViews.Member::class)
    @Column(name = "phone")
    var phone: String? = null

    @JsonView(UserViews.Member::class)
    @Basic(optional = false)
    @Column(name = "status")
    var status = 0

    @JsonView(UserViews.Member::class)
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    var createTime: Date? = null

    @JsonView(UserViews.Member::class)
    @Basic(optional = false)
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    var updateTime: Date? = null

    @JsonView(UserViews.Admin::class)
    @Column(name = "is_deleted")
    var isDeleted = 0

    companion object {
        private const val serialVersionUID = 1L
    }
}