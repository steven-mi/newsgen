import React from 'react';
import 'antd/dist/antd.css';
import './copyright.css';
import {Layout} from 'antd';

const {Footer} = Layout;

class Copyright extends React.Component {
    render() {
        return (
            <Footer style={{textAlign: 'center'}}>newsgen ©2020 Created by newspipe</Footer>
        );
    }
}

export default Copyright