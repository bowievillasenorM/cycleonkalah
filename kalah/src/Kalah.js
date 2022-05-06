import { Component } from "react"
import { Container } from "react-bootstrap";

export default class Kalah extends Component {
    render() {
        return (
            <div>
                <Container>
                    <h1>Kalah Game</h1>
                    <p>The game provides a Kalah board and a number of seeds or counters. The board has 6 small pits, called houses, on each side; and a big pit, called an end zone or store, at each end. The object of the game is to capture more seeds than one's opponent.

                        At the beginning of the game, four seeds are placed in each house. This is the traditional method.
                        Each player controls the six houses and their seeds on the player's side of the board. The player's score is the number of seeds in the store to their right.
                        Players take turns sowing their seeds. On a turn, the player removes all seeds from one of the houses under their control. Moving counter-clockwise, the player drops one seed in each house in turn, including the player's own store but not their opponent's.
                        If the last sown seed lands in an empty house owned by the player, and the opposite house contains seeds, both the last seed and the opposite seeds are captured and placed into the player's store.
                        If the last sown seed lands in the player's store, the player gets an additional move. There is no limit on the number of moves a player can make in their turn.
                        When one player no longer has any seeds in any of their houses, the game ends. The other player moves all remaining seeds to their store, and the player with the most seeds in their store wins.
                        It is possible for the game to end in a draw.
                    </p>
                </Container>
            </div>
        );
    }
}