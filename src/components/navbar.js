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
                    <Menu.Item>
                        <a href="https://github.com/newspipe"
                           style={{display: "table-cell"}} target="_blank"
                           rel="noopener noreferrer">
                            code
                        </a>
                    </Menu.Item>
                    <Menu.Item>
                        <a href="https://github.com/NewsPipe/gpt2-tfx-pipeline/releases"
                           style={{display: "table-cell"}} target="_blank"
                           rel="noopener noreferrer">
                            models
                        </a>
                    </Menu.Item>
                </Menu>
            </Header>
        );
    }
}

export default Navbar