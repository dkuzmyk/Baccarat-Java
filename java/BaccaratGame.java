import javafx.application.Application;
import java.awt.*;
import java.io.FileInputStream;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.VBox;

import static java.lang.Double.parseDouble;


public class BaccaratGame extends Application {

	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;

	double currentBet = 0;
	double totalWinnings = 0;
	double winner = 0;

	Card newCard;

	boolean betOnPlayer = false;
	boolean betOnBanker = false;
	boolean betOnDraw = false;
	boolean gameOver = false;

	Pane pane;

	// picture handling
	Image playerCard1;
	ImageView card1;

	Image playerCard2;
	ImageView card2;

	Image playerCard3;
	ImageView card3;

	Image bankerCard1;
	ImageView card4;

	Image bankerCard2;
	ImageView card5;

	Image bankerCard3;
	ImageView card6;

	ImageView banker1;
	Image backCard;

	BaccaratDealer theDealer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Let's Play Baccarat!!!");

		pane = new Pane();
		Image bg = new Image("file:src/main/resources/background.png");
		ImageView background = new ImageView();
		background.setImage(bg);
		pane.getChildren().add(background);

		// button management
		Button startButton = new Button("Start the Game!");
		Button startBid = new Button("Bet");

		// button design
		startButton.setStyle("-fx-background-color: red; -fx-border-width: 3px; -fx-border-color: black; -fx-font-size: 2em; -fx-text-fill: black;");
		startButton.setPrefSize(200, 50);
		startButton.setLayoutX(350);
		startButton.setLayoutY(400);

		startBid.setStyle("-fx-background-color: E84817; -fx-border-width: 5px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
		startBid.setPrefSize(60, 60);
		startBid.setLayoutX(470);
		startBid.setLayoutY(400);


		// text management
		Text textBid = new Text("How much do you want to bet?");
		Text textYouWin = new Text("Congratulations!! You Win!");
		Text textLose = new Text("Better luck next time!");
		Text textPlayerWin = new Text("Player Wins!");
		Text textBankerWin = new Text("Banker Wins!");
		Text textDraw = new Text("It's a Draw!!");

		// style management
		textBid.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		//textBid.setStyle("-fx-font-size: 18;"+"-fx-border-size: 500;"+"-fx-border-color: white;");
		textBid.setY(352);
		textBid.setX(180);

		textPlayerWin.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		//textPlayerWin.setStyle("-fx-text-base-color: White;");
		textPlayerWin.setY(354);
		textPlayerWin.setX(345);

		textBankerWin.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		//textBankerWin.setStyle("-fx-text-base-color: White;");
		textBankerWin.setY(352);
		textBankerWin.setX(345);

		textDraw.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		//textDraw.setStyle("-fx-text-base-color: Yellow;");
		textDraw.setY(352);
		textDraw.setX(345);

		textYouWin.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		//textDraw.setStyle("-fx-text-base-color: Yellow;");
		textYouWin.setY(570);
		textYouWin.setX(210);

		textLose.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		//textDraw.setStyle("-fx-text-base-color: Yellow;");
		textLose.setY(570);
		textLose.setX(270);


		pane.getChildren().add(startButton);

		startButton.setOnAction(actionEvent -> {
			pane.getChildren().remove(startButton);
			//while(!gameOver){
			// make it pause
			pane.getChildren().add(startBid);
			TextField bidding = new TextField();
			bidding.setLayoutX(370);
			bidding.setLayoutY(400);
			bidding.setPrefSize(100, 60);

			pane.getChildren().add(bidding);
			pane.getChildren().add(textBid);
			displayDeck();

			startBid.setOnAction(e -> {
				//pane.getChildren().retainAll(menu, textBid, startBid, bidding);

				currentBet = parseDouble(bidding.getText());
				System.out.print("Betting ");
				System.out.println(currentBet);
				TextField history = new TextField();
				history.setDisable(true);
				history.setPrefSize(180, 400);
				history.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
				history.setLayoutX(5);
				history.setLayoutY(200);
				history.setText("Bidding $" + currentBet + "\n");
				pane.getChildren().add(history);

				pane.getChildren().remove(startBid);
				pane.getChildren().remove(textBid);
				pane.getChildren().remove(bidding);

				// totalwinnings display
				TextField totalW = new TextField();
				totalW.setDisable(true);
				totalW.setPrefSize(180,100);
				totalW.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
				totalW.setLayoutX(5);
				totalW.setLayoutY(500);
				totalW.setText("Total Win $"+totalWinnings);
				pane.getChildren().add(totalW);

				// choose who to bid on
				Button bidOnPlayer = new Button("Bet on Player");
				Button bidOnBanker = new Button("Bet on Banker");
				Button bidOnDraw = new Button("Bet on Draw");

				// buttons graphic
				bidOnPlayer.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
				bidOnPlayer.setPrefSize(200, 60);
				bidOnPlayer.setLayoutX(350);
				bidOnPlayer.setLayoutY(300);

				bidOnBanker.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
				bidOnBanker.setPrefSize(200, 60);
				bidOnBanker.setLayoutX(350);
				bidOnBanker.setLayoutY(390);

				bidOnDraw.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
				bidOnDraw.setPrefSize(200, 60);
				bidOnDraw.setLayoutX(350);
				bidOnDraw.setLayoutY(480);

				pane.getChildren().add(bidOnPlayer);
				pane.getChildren().add(bidOnBanker);
				pane.getChildren().add(bidOnDraw);

				bidOnPlayer.setOnAction(r -> {
					pane.getChildren().remove(bidOnPlayer);
					pane.getChildren().remove(bidOnBanker);
					pane.getChildren().remove(bidOnDraw);
					betOnPlayer = true;

					theDealer = new BaccaratDealer();
					BaccaratGameLogic gameLogic = new BaccaratGameLogic();
					theDealer.generateDeck();
					theDealer.shuffleDeck();

					TextField playerPoints = new TextField();
					playerPoints.setLayoutX(365);
					playerPoints.setLayoutY(420);
					playerPoints.setPrefSize(60, 60);
					playerPoints.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					playerPoints.setDisable(true);
					pane.getChildren().add(playerPoints);

					TextField bankerPoints = new TextField();
					bankerPoints.setLayoutX(470);
					bankerPoints.setLayoutY(420);
					bankerPoints.setPrefSize(60, 60);
					bankerPoints.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					bankerPoints.setDisable(true);
					pane.getChildren().add(bankerPoints);

					playerHand = new ArrayList<Card>();
					bankerHand = new ArrayList<Card>();

					playerHand = theDealer.dealHand();

					//sleep(500);

					playerCard1 = new Image("file:src/main/resources/" + playerHand.get(0).getValue() +
							playerHand.get(0).getSuite() + ".png");
					card1 = new ImageView(playerCard1);
					card1.setFitHeight(175);
					card1.setFitWidth(120);
					card1.setX(222);
					card1.setY(150);
					pane.getChildren().add(card1);

					// PAUSE!!!

					//sleep(500);

					playerCard2 = new Image("file:src/main/resources/" + playerHand.get(1).getValue() +
							playerHand.get(1).getSuite() + ".png");
					card2 = new ImageView(playerCard2);
					card2.setFitHeight(175);
					card2.setFitWidth(120);
					card2.setX(222);
					card2.setY(361);
					pane.getChildren().add(card2);

					System.out.println("Player card 1: " + playerHand.get(0).getValue() % 10);
					System.out.println("Player card 2: " + playerHand.get(1).getValue() % 10);

					// PAUSE!!!!!!!!

					if (gameLogic.evaluatePlayerDraw(playerHand)) {
						//sleep(1500);
						newCard = theDealer.drawOne();
						playerHand.add(newCard);
						System.out.println("Player card 3: " + playerHand.get(2).getValue() % 10);
						playerCard3 = new Image("file:src/main/resources/" + playerHand.get(2).getValue() +
								playerHand.get(2).getSuite() + ".png");
						card3 = new ImageView(playerCard3);
						card3.setFitHeight(175);
						card3.setFitWidth(120);
						card3.setX(222);
						card3.setY(577);
						pane.getChildren().add(card3);
					}

					bankerHand = theDealer.dealHand();

					//sleep(500);

					bankerCard1 = new Image("file:src/main/resources/" + bankerHand.get(0).getValue() +
							bankerHand.get(0).getSuite() + ".png");
					card4 = new ImageView(bankerCard1);
					card4.setFitHeight(175);
					card4.setFitWidth(120);
					card4.setX(560);
					card4.setY(150);
					pane.getChildren().add(card4);

					//sleep(500);

					bankerCard2 = new Image("file:src/main/resources/" + bankerHand.get(1).getValue() +
							bankerHand.get(1).getSuite() + ".png");
					card5 = new ImageView(bankerCard2);
					card5.setFitHeight(175);
					card5.setFitWidth(120);
					card5.setX(560);
					card5.setY(361);
					pane.getChildren().add(card5);

					System.out.println("Banker card 1: " + bankerHand.get(0).getValue() % 10);
					System.out.println("Banker card 2: " + bankerHand.get(1).getValue() % 10);

					if (gameLogic.evaluateBankerDraw(bankerHand, newCard)) {
						//sleep(1500);
						Card dealerCard3 = theDealer.drawOne();
						bankerHand.add(dealerCard3);
						System.out.println("Banker card 3: " + bankerHand.get(2).getValue() % 10);
						bankerCard3 = new Image("file:src/main/resources/" + bankerHand.get(2).getValue() +
								bankerHand.get(2).getSuite() + ".png");
						card6 = new ImageView(bankerCard3);
						card6.setFitHeight(175);
						card6.setFitWidth(120);
						card6.setX(560);
						card6.setY(577);
						pane.getChildren().add(card6);
					}

					if (gameLogic.evaluatePlayerDraw(playerHand)) {
						//sleep(500);
						System.out.println("player card 3: " + playerHand.get(2).getValue());
					}

					if (gameLogic.evaluateBankerDraw(bankerHand, newCard)) {
						//sleep(500);
						System.out.println("banker card 3: " + bankerHand.get(2).getValue());
					}

					//sleep(500);

					System.out.println("Player total: " + gameLogic.handTotal(playerHand));        // system
					System.out.println("Banker total: " + gameLogic.handTotal(bankerHand));        // system

					playerPoints.setText(String.valueOf(gameLogic.handTotal(playerHand)));        // score
					bankerPoints.setText(String.valueOf(gameLogic.handTotal(bankerHand)));        // score

					double winner = evaluateWinnings();


					if (gameLogic.whoWon(playerHand, bankerHand) == "Player") {
						if (betOnPlayer) {
							pane.getChildren().add(textPlayerWin);
							pane.getChildren().add(textYouWin);
							history.setText("Won $" + winner + "\n");
							totalWinnings = totalWinnings + winner;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You win! $"+winner);
							System.out.println("Total Winning: " + totalWinnings);
						}
						else{System.out.println("Total Winning: " + 0);}
					}
					if (gameLogic.whoWon(playerHand, bankerHand) == "Banker") {
						if (betOnPlayer) {
							pane.getChildren().add(textBankerWin);
							pane.getChildren().add(textLose);
							history.setText("Lost $" + currentBet + "\n");
							totalWinnings = totalWinnings - currentBet;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You lose! $"+currentBet);
							System.out.println("Total Winning: " + totalWinnings);
						}
					}
					if (gameLogic.whoWon(playerHand, bankerHand) == "Draw") {
						if (betOnPlayer) {
							pane.getChildren().add(textDraw);
							pane.getChildren().add(textLose);
							history.setText("Lost $" + currentBet + "\n");
							totalWinnings = totalWinnings - currentBet;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You lose! $"+currentBet);
							System.out.println("Total Winning: " + totalWinnings);
						}
					}



					Text continuePlay = new Text("Continue?");
					continuePlay.setFont(Font.font("Arial", FontWeight.BOLD, 20));
					continuePlay.setLayoutX(410);
					continuePlay.setLayoutY(680);

					Button continuePlayNo = new Button("No");
					continuePlayNo.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
					continuePlayNo.setPrefSize(70, 50);
					continuePlayNo.setLayoutX(470);
					continuePlayNo.setLayoutY(700);
					continuePlayNo.setOnAction(exit->{
						gameOver = true;
						System.exit(0);
					});

					Button continuePlayYes = new Button("Yes");
					continuePlayYes.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
					continuePlayYes.setPrefSize(70, 50);
					continuePlayYes.setLayoutX(370);
					continuePlayYes.setLayoutY(700);
					continuePlayYes.setOnAction(keepGoing->{
						pane.getChildren().remove(continuePlayYes);
						pane.getChildren().remove(continuePlayNo);
						pane.getChildren().remove(continuePlay);
						try {
							//primaryStage.close();
							start(primaryStage);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					});

					pane.getChildren().add(continuePlay);
					pane.getChildren().add(continuePlayYes);
					pane.getChildren().add(continuePlayNo);

				});

				bidOnBanker.setOnAction(r -> {
					pane.getChildren().remove(bidOnPlayer);
					pane.getChildren().remove(bidOnBanker);
					pane.getChildren().remove(bidOnDraw);
					betOnBanker = true;

					theDealer = new BaccaratDealer();
					BaccaratGameLogic gameLogic = new BaccaratGameLogic();
					theDealer.generateDeck();
					theDealer.shuffleDeck();

					TextField playerPoints = new TextField();
					playerPoints.setLayoutX(365);
					playerPoints.setLayoutY(420);
					playerPoints.setPrefSize(60, 60);
					playerPoints.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					playerPoints.setDisable(true);
					pane.getChildren().add(playerPoints);

					TextField bankerPoints = new TextField();
					bankerPoints.setLayoutX(470);
					bankerPoints.setLayoutY(420);
					bankerPoints.setPrefSize(60, 60);
					bankerPoints.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					bankerPoints.setDisable(true);
					pane.getChildren().add(bankerPoints);

					playerHand = new ArrayList<Card>();
					bankerHand = new ArrayList<Card>();

					playerHand = theDealer.dealHand();

					playerCard1 = new Image("file:src/main/resources/" + playerHand.get(0).getValue() +
							playerHand.get(0).getSuite() + ".png");
					card1 = new ImageView(playerCard1);
					card1.setFitHeight(175);
					card1.setFitWidth(120);
					card1.setX(222);
					card1.setY(150);
					pane.getChildren().add(card1);

					// PAUSE!!!

					playerCard2 = new Image("file:src/main/resources/" + playerHand.get(1).getValue() +
							playerHand.get(1).getSuite() + ".png");
					card2 = new ImageView(playerCard2);
					card2.setFitHeight(175);
					card2.setFitWidth(120);
					card2.setX(222);
					card2.setY(361);
					pane.getChildren().add(card2);

					System.out.println("Player card 1: " + playerHand.get(0).getValue() % 10);
					System.out.println("Player card 2: " + playerHand.get(1).getValue() % 10);

					// PAUSE!!!!!!!!

					if (gameLogic.evaluatePlayerDraw(playerHand)) {
						newCard = theDealer.drawOne();
						playerHand.add(newCard);
						System.out.println("Player card 3: " + playerHand.get(2).getValue() % 10);
						playerCard3 = new Image("file:src/main/resources/" + playerHand.get(2).getValue() +
								playerHand.get(2).getSuite() + ".png");
						card3 = new ImageView(playerCard3);
						card3.setFitHeight(175);
						card3.setFitWidth(120);
						card3.setX(222);
						card3.setY(577);
						pane.getChildren().add(card3);
					}

					bankerHand = theDealer.dealHand();

					bankerCard1 = new Image("file:src/main/resources/" + bankerHand.get(0).getValue() +
							bankerHand.get(0).getSuite() + ".png");
					card4 = new ImageView(bankerCard1);
					card4.setFitHeight(175);
					card4.setFitWidth(120);
					card4.setX(560);
					card4.setY(150);
					pane.getChildren().add(card4);

					bankerCard2 = new Image("file:src/main/resources/" + bankerHand.get(1).getValue() +
							bankerHand.get(1).getSuite() + ".png");
					card5 = new ImageView(bankerCard2);
					card5.setFitHeight(175);
					card5.setFitWidth(120);
					card5.setX(560);
					card5.setY(361);
					pane.getChildren().add(card5);

					System.out.println("Banker card 1: " + bankerHand.get(0).getValue() % 10);
					System.out.println("Banker card 2: " + bankerHand.get(1).getValue() % 10);

					if (gameLogic.evaluateBankerDraw(bankerHand, newCard)) {
						Card dealerCard3 = theDealer.drawOne();
						bankerHand.add(dealerCard3);
						System.out.println("Banker card 3: " + bankerHand.get(2).getValue() % 10);
						bankerCard3 = new Image("file:src/main/resources/" + bankerHand.get(2).getValue() +
								bankerHand.get(2).getSuite() + ".png");
						card6 = new ImageView(bankerCard3);
						card6.setFitHeight(175);
						card6.setFitWidth(120);
						card6.setX(560);
						card6.setY(577);
						pane.getChildren().add(card6);
					}

					if (gameLogic.evaluatePlayerDraw(playerHand)) {
						System.out.println("player card 3: " + playerHand.get(2).getValue());
					}

					if (gameLogic.evaluateBankerDraw(bankerHand, newCard)) {
						System.out.println("banker card 3: " + bankerHand.get(2).getValue());
					}

					System.out.println("Player total: " + gameLogic.handTotal(playerHand));        // system
					System.out.println("Banker total: " + gameLogic.handTotal(bankerHand));        // system

					playerPoints.setText(String.valueOf(gameLogic.handTotal(playerHand)));        // score
					bankerPoints.setText(String.valueOf(gameLogic.handTotal(bankerHand)));        // score

					double winner = evaluateWinnings();


					if (gameLogic.whoWon(playerHand, bankerHand) == "Player") {
						if (betOnBanker) {
							pane.getChildren().add(textPlayerWin);
							pane.getChildren().add(textLose);
							history.setText("Lost $" + currentBet + "\n");
							totalWinnings = totalWinnings - winner;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You lose! $"+currentBet);
							System.out.println("Total Winning: " + totalWinnings);
						}
					}
					if (gameLogic.whoWon(playerHand, bankerHand) == "Banker") {
						if (betOnBanker) {
							pane.getChildren().add(textBankerWin);
							pane.getChildren().add(textYouWin);
							history.setText("Won $" + winner + "\n");
							totalWinnings = totalWinnings + winner;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You win!  $"+winner);
							System.out.println("Total Winning: " + totalWinnings);
						}
						else{System.out.println("Total Winning: " + 0);}
					}
					if (gameLogic.whoWon(playerHand, bankerHand) == "Draw") {
						if (betOnBanker) {
							pane.getChildren().add(textDraw);
							pane.getChildren().add(textLose);
							history.setText("Lost $" + currentBet + "\n");
							totalWinnings = totalWinnings - winner;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You lose! $"+currentBet);
							System.out.println("Total Winning: " + totalWinnings);
						}
					}


					Text continuePlay = new Text("Continue?");
					continuePlay.setFont(Font.font("Arial", FontWeight.BOLD, 20));
					continuePlay.setLayoutX(410);
					continuePlay.setLayoutY(680);

					Button continuePlayNo = new Button("No");
					continuePlayNo.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
					continuePlayNo.setPrefSize(70, 50);
					continuePlayNo.setLayoutX(470);
					continuePlayNo.setLayoutY(700);
					continuePlayNo.setOnAction(exit->{
						gameOver = true;
						System.exit(0);
					});

					Button continuePlayYes = new Button("Yes");
					continuePlayYes.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
					continuePlayYes.setPrefSize(70, 50);
					continuePlayYes.setLayoutX(370);
					continuePlayYes.setLayoutY(700);
					continuePlayYes.setOnAction(keepGoing->{
						pane.getChildren().remove(continuePlayYes);
						pane.getChildren().remove(continuePlayNo);
						pane.getChildren().remove(continuePlay);
						try {
							//primaryStage.close();
							start(primaryStage);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					});

					pane.getChildren().add(continuePlay);
					pane.getChildren().add(continuePlayYes);
					pane.getChildren().add(continuePlayNo);

				});

				bidOnDraw.setOnAction(r -> {
					pane.getChildren().remove(bidOnPlayer);
					pane.getChildren().remove(bidOnBanker);
					pane.getChildren().remove(bidOnDraw);
					betOnDraw = true;

					theDealer = new BaccaratDealer();
					BaccaratGameLogic gameLogic = new BaccaratGameLogic();
					theDealer.generateDeck();
					theDealer.shuffleDeck();

					TextField playerPoints = new TextField();
					playerPoints.setLayoutX(365);
					playerPoints.setLayoutY(420);
					playerPoints.setPrefSize(60, 60);
					playerPoints.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					playerPoints.setDisable(true);
					pane.getChildren().add(playerPoints);

					TextField bankerPoints = new TextField();
					bankerPoints.setLayoutX(470);
					bankerPoints.setLayoutY(420);
					bankerPoints.setPrefSize(60, 60);
					bankerPoints.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
					bankerPoints.setDisable(true);
					pane.getChildren().add(bankerPoints);

					playerHand = new ArrayList<Card>();
					bankerHand = new ArrayList<Card>();

					playerHand = theDealer.dealHand();

					playerCard1 = new Image("file:src/main/resources/" + playerHand.get(0).getValue() +
							playerHand.get(0).getSuite() + ".png");
					card1 = new ImageView(playerCard1);
					card1.setFitHeight(175);
					card1.setFitWidth(120);
					card1.setX(222);
					card1.setY(150);
					pane.getChildren().add(card1);

					// PAUSE!!!

					playerCard2 = new Image("file:src/main/resources/" + playerHand.get(1).getValue() +
							playerHand.get(1).getSuite() + ".png");
					card2 = new ImageView(playerCard2);
					card2.setFitHeight(175);
					card2.setFitWidth(120);
					card2.setX(222);
					card2.setY(361);
					pane.getChildren().add(card2);

					System.out.println("Player card 1: " + playerHand.get(0).getValue() % 10);
					System.out.println("Player card 2: " + playerHand.get(1).getValue() % 10);

					// PAUSE!!!!!!!!

					if (gameLogic.evaluatePlayerDraw(playerHand)) {
						newCard = theDealer.drawOne();
						playerHand.add(newCard);
						System.out.println("Player card 3: " + playerHand.get(2).getValue() % 10);
						playerCard3 = new Image("file:src/main/resources/" + playerHand.get(2).getValue() +
								playerHand.get(2).getSuite() + ".png");
						card3 = new ImageView(playerCard3);
						card3.setFitHeight(175);
						card3.setFitWidth(120);
						card3.setX(222);
						card3.setY(577);
						pane.getChildren().add(card3);
					}

					bankerHand = theDealer.dealHand();

					bankerCard1 = new Image("file:src/main/resources/" + bankerHand.get(0).getValue() +
							bankerHand.get(0).getSuite() + ".png");
					card4 = new ImageView(bankerCard1);
					card4.setFitHeight(175);
					card4.setFitWidth(120);
					card4.setX(560);
					card4.setY(150);
					pane.getChildren().add(card4);

					bankerCard2 = new Image("file:src/main/resources/" + bankerHand.get(1).getValue() +
							bankerHand.get(1).getSuite() + ".png");
					card5 = new ImageView(bankerCard2);
					card5.setFitHeight(175);
					card5.setFitWidth(120);
					card5.setX(560);
					card5.setY(361);
					pane.getChildren().add(card5);

					System.out.println("Banker card 1: " + bankerHand.get(0).getValue() % 10);
					System.out.println("Banker card 2: " + bankerHand.get(1).getValue() % 10);

					if (gameLogic.evaluateBankerDraw(bankerHand, newCard)) {
						Card dealerCard3 = theDealer.drawOne();
						bankerHand.add(dealerCard3);
						System.out.println("Banker card 3: " + bankerHand.get(2).getValue() % 10);
						bankerCard3 = new Image("file:src/main/resources/" + bankerHand.get(2).getValue() +
								bankerHand.get(2).getSuite() + ".png");
						card6 = new ImageView(bankerCard3);
						card6.setFitHeight(175);
						card6.setFitWidth(120);
						card6.setX(560);
						card6.setY(577);
						pane.getChildren().add(card6);
					}

					if (gameLogic.evaluatePlayerDraw(playerHand)) {
						System.out.println("player card 3: " + playerHand.get(2).getValue());
					}

					if (gameLogic.evaluateBankerDraw(bankerHand, newCard)) {
						System.out.println("banker card 3: " + bankerHand.get(2).getValue());
					}

					System.out.println("Player total: " + gameLogic.handTotal(playerHand));        // system
					System.out.println("Banker total: " + gameLogic.handTotal(bankerHand));        // system

					playerPoints.setText(String.valueOf(gameLogic.handTotal(playerHand)));        // score
					bankerPoints.setText(String.valueOf(gameLogic.handTotal(bankerHand)));        // score

					double winner = evaluateWinnings();


					if (gameLogic.whoWon(playerHand, bankerHand) == "Player") {
						if (betOnDraw) {
							pane.getChildren().add(textPlayerWin);
							pane.getChildren().add(textLose);
							history.setText("Lost $" + currentBet + "\n");
							totalWinnings = totalWinnings - currentBet;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You lose! $"+currentBet);
							System.out.println("Total Winning: " + totalWinnings);
						}
					}
					if (gameLogic.whoWon(playerHand, bankerHand) == "Banker") {
						if (betOnDraw) {
							pane.getChildren().add(textBankerWin);
							pane.getChildren().add(textLose);
							history.setText("Lost $" + currentBet + "\n");
							totalWinnings = totalWinnings - currentBet;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You lose! $"+currentBet);
							System.out.println("Total Winning: " + totalWinnings);
						}
					}
					if (gameLogic.whoWon(playerHand, bankerHand) == "Draw") {
						if (betOnDraw) {
							pane.getChildren().add(textDraw);
							pane.getChildren().add(textYouWin);
							history.setText("Won $" + winner + "\n");
							totalWinnings = totalWinnings + winner;
							totalW.setText("Total Win $"+totalWinnings);
							System.out.println("You win! $"+winner);
							System.out.println("Total Winning: " + totalWinnings);
						}
					}

					Text continuePlay = new Text("Continue?");
					continuePlay.setFont(Font.font("Arial", FontWeight.BOLD, 20));
					continuePlay.setLayoutX(410);
					continuePlay.setLayoutY(680);

					Button continuePlayNo = new Button("No");
					continuePlayNo.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
					continuePlayNo.setPrefSize(70, 50);
					continuePlayNo.setLayoutX(470);
					continuePlayNo.setLayoutY(700);
					continuePlayNo.setOnAction(exit->{
						gameOver = true;
						System.exit(0);
					});

					Button continuePlayYes = new Button("Yes");
					continuePlayYes.setStyle("-fx-background-color: red; -fx-border-width: 1px; -fx-border-color: black; -fx-font-size: 1.8em; -fx-text-fill: black;");
					continuePlayYes.setPrefSize(70, 50);
					continuePlayYes.setLayoutX(370);
					continuePlayYes.setLayoutY(700);
					continuePlayYes.setOnAction(keepGoing->{
						pane.getChildren().remove(continuePlayYes);
						pane.getChildren().remove(continuePlayNo);
						pane.getChildren().remove(continuePlay);

						try {
							//primaryStage.close();
							start(primaryStage);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					});

					pane.getChildren().add(continuePlay);
					pane.getChildren().add(continuePlayYes);
					pane.getChildren().add(continuePlayNo);

				});

				/*  DOESNT WORK PROPERLY, LOOKS LIKE THE GAME IS FROZEN, IT DOESNT SLEEP AT THE RIGHT TIMES
				try {
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
*/

			});
		//} // game over while loop
		});


		MenuBar Menu = new MenuBar();
		Menu mOne = new Menu("Settings");
		MenuItem exit = new MenuItem("Exit");
		MenuItem restart = new MenuItem("Restart");
		mOne.getItems().addAll(exit, restart);

		exit.setOnAction(quit->{
			System.exit(0);
		});

		restart.setOnAction(res->{
			totalWinnings = 0;
			System.out.println("Restarting!");
			try {
				//primaryStage.close();
				start(primaryStage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});


		Menu.getMenus().addAll(mOne);
		pane.getChildren().add(Menu);


        Scene scene = new Scene(pane,900,900);

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
        primaryStage.show();
	}

	void displayDeck(){
		backCard = new Image("file:src/main/resources/red_back.png");
		banker1 = new ImageView(backCard);
		banker1.setFitHeight(175);
		banker1.setFitWidth(120);
		//banker1.setPreserveRatio(true);
		//banker1.setY(148);
		//banker1.setX(560);
		banker1.setY(350);
		banker1.setX(740);
		pane.getChildren().add(banker1);
	}

	public static void sleep(int time){
		try{
			Thread.sleep(time);
		} catch (InterruptedException e){}

	}

	double evaluateWinnings(){
		BaccaratGameLogic gameLogic = new BaccaratGameLogic();
		if(gameLogic.whoWon(playerHand, bankerHand) == "Player"){
			if(betOnPlayer){
				winner = currentBet + currentBet*0.95;
				return winner;	// 5% commission
			}

			else return 0;
		}

		else if(gameLogic.whoWon(playerHand, bankerHand) == "Banker"){
			if(betOnBanker){
				winner = currentBet + currentBet*0.95;
				return winner;
			}

			else return 0;
		}

		else if(gameLogic.whoWon(playerHand, bankerHand) == "Draw"){
			if(betOnDraw) {
				winner = currentBet + currentBet*0.95;
				return winner;
			}
			else return 0;
		}
		else return 0;
	}

}
