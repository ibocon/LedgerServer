import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from "react-router"
import { Link } from 'react-router-dom'

import { Layout, Menu } from 'antd'
import {
    MenuUnfoldOutlined,
    MenuFoldOutlined,
  } from '@ant-design/icons'

import { isEmpty } from '../util'
import { UserAction } from '../action'
import { UrlConstant } from '../constant'

const { Header } = Layout

class _MyHeader extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            collapsed: false,
        }
    }

    onCollapse(collapsed) {
        this.setState({ collapsed })
    }

    render() {
        const { user, logout } = this.props
        const googleAuthUrl = UrlConstant.GOOGLE_AUTH_URL
        return (   
            <Header className="site-layout-background" style={{ padding: 0 }}>
                <Menu theme="light" mode="horizontal">
                    {
                        isEmpty(user) &&
                        <Menu.Item style={{float: 'right'}} >
                            <a href={googleAuthUrl}><span>로그인</span></a>
                        </Menu.Item>
                    }
                    {
                        !isEmpty(user) &&
                        <Menu.Item style={{float: 'right'}} onClick={logout} >
                            <span>로그아웃</span>
                        </Menu.Item>
                    }
                </Menu>
          </Header>
        )
    }
}
const mapStateToProps = state => ({
    user: state.user
})

const mapDispatchToProps = dispatch => ({
    logout: () => {
        dispatch(UserAction.logout());
    }
})

export const MyHeader = withRouter(connect(mapStateToProps, mapDispatchToProps)(_MyHeader))