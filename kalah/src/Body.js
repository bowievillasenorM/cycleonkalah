import { Component, Fragment } from "react"
import { Row, Button, Accordion, Badge } from "react-bootstrap";
import Pit from "../src/pit.JPG";
import player1Store from "../src/player1Store.JPG";
import player2Store from "../src/player2Store.JPG";

export default class Body extends Component {


    constructor(props) {
        super(props);
        this.state = {
            pagedata: {
                housesAndStores: [],
                playerToMove: "PLAYER1",
                type: "STANDARD",
                winningPlayer: ""
            },
            numOfHouses: 6,
            numOfSeeds: 4
        };
        this.onChangeHouseField = this.onChangeHouseField.bind(this);
        this.onChangeSeedsFields = this.onChangeSeedsFields.bind(this);

    }

    initialize() {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        };
        fetch('http://localhost:8080/kalahgame/0/start?houses=' + this.state.numOfHouses + '&seeds=' + this.state.numOfSeeds, requestOptions)
            .then(response => response.json())
            .then(data => this.setState({ pagedata: data }));
    }

    restart() {
        this.initialize();
    }

    componentDidMount() {
        this.initialize();
    }

    checkIfNumeric(input) {
        const re = /^[0-9\b]+$/;
        if (input === '' || re.test(input)) {
            return true;
        }
    }

    onChangeHouseField(e) {
        const re = /^[0-9\b]+$/;
        if (this.checkIfNumeric(e.target.value)) {
            this.setState({ numOfHouses: e.target.value })
        }

    }

    onChangeSeedsFields(e) {
        if (this.checkIfNumeric(e.target.value)) {
            this.setState({ numOfSeeds: e.target.value })
        }
    }

    sow(index) {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        };
        fetch('http://localhost:8080/kalahgame/0/sow/' + index + '', requestOptions)
            .then(response => response.json())
            .then(data => this.setState({ pagedata: data }));
    }

    getPlayer1Houses() {
        let numberOfHouses = this.state.pagedata.housesAndStores.length / 2 - 1;
        let items = [];
        let ctr = 0;

        while (ctr < numberOfHouses) {
            const j = ctr;
            items.push(<div onClick={() => this.sow(j)} className="pits"><div >Seeds : {this.state.pagedata.housesAndStores[j]} </div><div><img className="pitImage" src={Pit} /></div></div>)
            ctr++;
        }

        return (
            <Fragment>
                {
                    items
                }
            </Fragment>
        )
    }

    getPlayer1Store() {
        let index = this.state.pagedata.housesAndStores.length / 2 - 1;
        return (<div onClick={() => this.sow(index)} className="stores1"><img className="pitImage" src={player1Store} />Store 1: {this.state.pagedata.housesAndStores[index]} </div>);
    }

    getPlayer2Store() {
        let index = this.state.pagedata.housesAndStores.length - 1;
        return (<div onClick={() => this.sow(index)} className="stores2">Store 2: {this.state.pagedata.housesAndStores[index]}<img className="pitImage" src={player2Store} /></div>)
    }

    getPlayer2Houses() {
        let player2Store = this.state.pagedata.housesAndStores.length;
        let player1Store = player2Store / 2 - 1;
        let items = [];
        let ctr = player2Store - 2;

        while (ctr > player1Store) {
            const j = ctr;
            items.push(<div onClick={() => this.sow(j)} className="pits"><img className="pitImage" src={Pit} /><div><div >Seeds : {this.state.pagedata.housesAndStores[j]} </div></div></div>)
            ctr--;
        }

        return (
            <Fragment>
                {
                    items
                }
            </Fragment>
        )
    }

    isPlayer1Turn(){
        if(this.state.pagedata.playerToMove==='PLAYER1') {
            return (
                <Badge bg="success">Turn to move</Badge>
            )
        }
    }

    isPlayer2Turn(){
        if(this.state.pagedata.playerToMove==='PLAYER2') {
            return (
                <Badge bg="success">Turn to move</Badge>
            )
        }
    }

    render() {
        return (
            <div>
                <div>
                    <div className="gameStatus">
                        <Accordion defaultActiveKey="0" flush>
                            <Accordion.Item eventKey="0">
                                <Accordion.Header>Game satus</Accordion.Header>
                                <Accordion.Body>
                                    <div>Game winner  : {this.state.pagedata.winningPlayer}</div>
                                    <div>Game type    : {this.state.pagedata.type}</div>
                                </Accordion.Body>
                            </Accordion.Item>
                            <Accordion.Item eventKey="1">
                                <Accordion.Header>Restart game</Accordion.Header>
                                <Accordion.Body>
                                    <label>Number of houses</label>
                                    <input value={this.state.numOfHouses} onChange={this.onChangeHouseField} type="text" />
                                    <label>Number of seeds</label>
                                    <input value={this.state.numOfSeeds} onChange={this.onChangeSeedsFields} type="text" />
                                    <Button onClick={() => this.restart()} variant="primary" type="submit">
                                        Restart
                                    </Button>
                                </Accordion.Body>
                            </Accordion.Item>
                        </Accordion>
                    </div>
                    <Row>
                        <div>Player 1 {this.isPlayer1Turn()}</div>  
                    </Row>
                    <div className="player1Row">
                        {this.getPlayer1Houses()}
                        {this.getPlayer1Store()}
                    </div>
                    <div className="player2Row">
                        {this.getPlayer2Store()}
                        {this.getPlayer2Houses()}
                    </div>
                    <Row>
                    <div>Player 2 {this.isPlayer2Turn()}</div> 
                    </Row>
                </div>
            </div>
        );
    }
}