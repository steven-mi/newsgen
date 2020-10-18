import React from 'react';
import 'antd/dist/antd.css';
import './body.css';
import {Layout} from 'antd';
import {Row, Col} from 'antd';
import {Menu, Dropdown} from 'antd';
import {DownOutlined, LoadingOutlined} from '@ant-design/icons';
import {Spin} from 'antd';
import {Modal} from 'antd';

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
            calculating: false,
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

    antIcon = <LoadingOutlined style={{fontSize: 24}} spin/>;

    handleChange(event) {
        this.postRequestGPT2(event.target.value);
    }

    async setModel(modelName) {
        const requestOptions = {
            method: 'PUT',
        };
        this.setState({modelName: modelName})
        await fetch(this.state.requestURL + '/gpt2/model/' + this.state.modelName, requestOptions);
    }

    async getAllModels() {
        const requestOptions = {
            method: 'GET',
        };
        const response = await fetch(this.state.requestURL + '/gpt2/models', requestOptions);
        const data = await response.text();
        this.setState({modelList: data.split(",")})
    }

    async getModel() {
        const requestOptions = {
            method: 'GET',
        };
        const response = await fetch(this.state.requestURL + '/gpt2/model', requestOptions);
        const data = await response.text();
        this.setState({modelName: data})
    }

    async postRequestGPT2(text) {
        text = text.toString()
        if (text.length > 0 || !this.state.calculating || text !== this.state.inputText) {
            this.setState({inputText: text});

            const requestOptions = {
                method: 'POST',
                headers: {'Content-Type': 'text/plain'},
                body: text
            };

            let predicted = false;
            this.setState({calculating: true})
            try {
                const response = await fetch(this.state.requestURL + '/gpt2/predict', requestOptions);
                const data = await response.text();
                predicted = true;
            } catch (error) {
                console.log(error)
            }
            this.setState({calculating: false})
            if (predicted){
                this.setState({predictedText: data})
            }
        }
    }

    render() {
        return (
            <Content>
                <Modal visible={this.state.calculating}
                       footer={null}
                       closable={false}>
                    <Spin indicator={this.antIcon} tip="Generating text..." size="large">
                    </Spin>
                </Modal>
                <Row>
                    <Col span={12}>
                        <p className="newsgen-p-source">Generate text from:</p>
                        <textarea className="newsgen-textarea-source"
                                  onBlur={this.handleChange.bind(this)}>
                            </textarea>
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
