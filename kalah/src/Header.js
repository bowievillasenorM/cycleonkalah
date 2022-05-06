import { Component } from "react"
import { Navbar, Nav } from "react-bootstrap";

export default class Header extends Component {
    render() {
        return (
            <div className="headerHeader">
                <Navbar bg="light" expand="lg">
                    <Navbar.Brand href="#home">Cycleon Board Games</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto">
                            <Nav.Link href="/">Home</Nav.Link>
                            <Nav.Link href="/kalah">Kahla</Nav.Link>
                            <Nav.Link href="/chess">Chess</Nav.Link>
                            <Nav.Link href="/monopoly">Monopoly</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
            </div>
        );
    }
}