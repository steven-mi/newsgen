import React from 'react';
import 'antd/dist/antd.css';
import './navbar.css';
import {Layout, Menu} from 'antd';

const {Header} = Layout;

class Navbar extends React.Component {
    render() {
        return (
            <Header>
                <div className="logo">newsgen</div>
                <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
                    <Menu.Item><a href="https://github.com/newspipe">code</a></Menu.Item>
                    <Menu.Item>models</Menu.Item>
                </Menu>
            </Header>
        );
    }
}

export default Navbar