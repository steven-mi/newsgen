import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import './index.css';
import {Layout} from 'antd';


import Navbar from './components/navbar'
import Body from "./components/body";
import Copyright from "./components/copyright";

class App extends React.Component {
    render() {
        return (
            <Layout>
                <Navbar/>
                <Body/>
                <Copyright/>
            </Layout>
        );
    }
}


ReactDOM.render(
    <App/>,
    document.getElementById('root'),
)
;


//ReactDOM.render(
//    <h1>Hello, world!</h1>,
//    document.getElementById('root')
//);