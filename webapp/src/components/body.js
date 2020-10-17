import React from 'react';
import 'antd/dist/antd.css';
import './body.css';
import {Layout} from 'antd';
import {Row, Col} from 'antd';
import {Menu, Dropdown} from 'antd';
import {DownOutlined} from '@ant-design/icons';

const {Content} = Layout;

class Body extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            inputText: '',
            predictedText: '',
            modelList: [],
            modelName: '',
            requestURL: 'http://localhost:9000',
        }
        if (process.env.REACT_APP_GPT2_SERVICE_DOMAIN !== undefined) {
            this.state.requestURL = process.env.REACT_APP_GPT2_SERVICE_DOMAIN
            if (!this.state.requestURL.startsWith("http://")) {
                this.state.requestURL = "http://" + this.state.requestURL
            }
        }
        this.getAllModels();
        this.getModel();
    }

    onClick = (key) => {
        if (key.key !== undefined) {
            this.setModel(key.key);
        }
    };

    menu = (object) => (
        <Menu onClick={this.onClick}>
            {object.state.modelList.map((model) =>
                <Menu.Item key={model}>
                    {model}
                </Menu.Item>
            )}
        </Menu>
    );


    handleChange(event) {
        this.setState({inputText: event.target.value});
        this.postRequestGPT2(this.state.inputText);
    }

    async setModel(id) {
        const requestOptions = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
        };
        this.setState({modelName: id})
        await fetch(this.state.requestURL + '/model/' + this.state.modelName, requestOptions);
    }

    async getAllModels() {
        const requestOptions = {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        };
        const response = await fetch(this.state.requestURL + '/models', requestOptions);
        const data = await response.json();
        this.setState({modelList: data})
    }

    async getModel() {
        const requestOptions = {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        };
        const response = await fetch(this.state.requestURL + '/model', requestOptions);
        const data = await response.json();
        this.setState({modelName: data})
    }

    async postRequestGPT2(text) {
        text = text.toString()
        if (text.length > 0) {
            // Simple POST request with a JSON body using fetch
            const requestOptions = {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({"text": text})
            };
            const response = await fetch(this.state.requestURL + '/predict', requestOptions);
            const data = await response.json();
            this.setState({predictedText: data.prediction})
        }
    }

    render() {
        return (
            <Content>
                <Row>
                    <Col span={12}>
                        <p className="newsgen-p-source">Generate text from:</p>
                        <textarea className="newsgen-textarea-source"
                                  onBlur={this.handleChange.bind(this)}/>
                    </Col>
                    <Col span={12}>
                        <div className="newsgen-layout-p-sameline ">
                            <p className="newsgen-p-target">Generate text with: </p>
                            <Dropdown overlay={this.menu(this)} trigger={['click']}>
                                <p className="newsgen-p-model"
                                   onClick={e => e.preventDefault()}>
                                    {this.state.modelName}
                                    <DownOutlined/>
                                </p>
                            </Dropdown>
                        </div>
                        <textarea className="newsgen-textarea-target"
                                  defaultValue={this.state.predictedText}>
                            </textarea>
                    </Col>
                </Row>
            </Content>
        );
    }
}

export default Body
