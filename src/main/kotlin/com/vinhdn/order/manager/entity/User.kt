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

    @Id
    @JsonView(UserViews.Member::class)
    @Column(name = "username")
    var username: String? = null

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

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val userShops: List<UserShop>? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}