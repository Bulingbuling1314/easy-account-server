package com.example.easyaccount.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
public class UserEntity implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String openId;

        private String nickname;

        @Column(name = "avatar_url")
        private String avatarUrl;

        private Integer gender;

        @CreationTimestamp
        @Column(name = "create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getOpenId() {
                return openId;
        }

        public void setOpenId(String openId) {
                this.openId = openId;
        }

        public String getNickname() {
                return nickname;
        }

        public void setNickname(String nickname) {
                this.nickname = nickname;
        }

        public String getAvatarUrl() {
                return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
        }

        public Integer getGender() {
                return gender;
        }

        public void setGender(Integer gender) {
                this.gender = gender;
        }

        public LocalDateTime getCreateTime() {
                return createTime;
        }

        public void setCreateTime(LocalDateTime createTime) {
                this.createTime = createTime;
        }
}
