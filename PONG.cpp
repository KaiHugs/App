//2022-02-18
//Kai Hughes
//RUN IN TERMINAL

#include <iostream>
#include <time.h>
#include <conio.h>
using namespace std;
enum eDir { STOP = 0, LEFT = 1, UPLEFT = 2, DOWNLEFT = 3, RIGHT = 4, UPRIGHT = 5, DOWNRIGHT = 6};
class Pong
{
private:
	int x, y, firstX, firstY;
	eDir direction;
public:
	Pong(int posX, int posY)
	{
		firstX = posX;
		firstY = posY;
		x = posX; y = posY;
		direction = STOP;
	}
	void Start_Over()
	{
		x = firstX; y = firstY;
		direction = STOP;
	}
	void changeDirection(eDir d)
	{
		direction = d;
	}
	void randomDirection()
	{
		direction = (eDir)((rand() % 6) + 1);
	}
	inline int getX() { return x; }
	inline int getY() { return y; }
	inline eDir getDirection() { return direction; }
	void Move()
	{
		switch (direction)
		{
		case STOP:
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		case UPLEFT:
			x--; y--;
			break;
		case DOWNLEFT:
			x--; y++;
			break;
		case UPRIGHT:
			x++; y--;
			break;
		case DOWNRIGHT:
			x++; y++;
			break;
		default:
			break;
		}
	}
	friend ostream & operator<<(ostream & o, Pong c)
	{
		o << "Ball [" << c.x << "," << c.y << "][" << c.direction << "]";
		return o;
	}
};
class cPaddle
{
private:
	int x, y;
	int firstX, firstY;
public:
	cPaddle()
	{
		x = y = 0;
	}
	cPaddle(int posX, int posY) : cPaddle()
	{
		firstX = posX;
		firstY = posY;
		x = posX;
		y = posY;
	}
	inline void Start_Over() { x = firstX; y = firstY; }
	inline int getX() { return x; }
	inline int getY() { return y; }
	inline void moveUp() { y--; }
	inline void moveDown() { y++; }
	friend ostream & operator<<(ostream & o, cPaddle c)
	{
		o << "Paddle [" << c.x << "," << c.y << "]";
		return o;
	}
};
class cGameManger
{
private:
	int length, width;
	int score1, score2;
	char up1, down1, up2, down2;
	bool quit;
	Pong * ball;
	cPaddle *player1;
	cPaddle *player2;
public:
	cGameManger(int l, int w)
	{
		srand(time(NULL));
		quit = false;
		up1 = 'w'; up2 = 'i';
		down1 = 's'; down2 = 'k';
		score1 = score2 = 0;
		length = l; width = w;
		ball = new Pong(w / 2, w / 2);
		player1 = new cPaddle(1, w / 2 - 3);
		player2 = new cPaddle(w - 2, w / 2 - 3);
	}
	~cGameManger()
	{
		delete ball, player1, player2;
	}
	void ScoreUp(cPaddle * player)
	{
		if (player == player1)
			score1++;
		else if (player == player2)
			score2++;

		ball->Start_Over();
		player1->Start_Over();
		player2->Start_Over();
	}
	void Draw()
	{
		system("cls");
		for (int i = 0; i < width + 2; i++)
			cout << "\xB2";
		cout << endl;

		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < width; j++)
			{
				int ballx = ball->getX();
				int bally = ball->getY();
				int player1x = player1->getX();
				int player2x = player2->getX();
				int player1y = player1->getY();
				int player2y = player2->getY();

				if (j == 0)
					cout << "\xB2";

				if (ballx == j && bally == i)
					cout << "O"; 
				else if (player1x == j && player1y == i)
					cout << "\xDB"; 
				else if (player2x == j && player2y == i)
					cout << "\xDB"; 

				else if (player1x == j && player1y + 1 == i)
					cout << "\xDB"; 
				else if (player1x == j && player1y + 2 == i)
					cout << "\xDB"; 
				else if (player1x == j && player1y + 3 == i)
					cout << "\xDB"; 

				else if (player2x == j && player2y + 1 == i)
					cout << "\xDB"; 
				else if (player2x == j && player2y + 2 == i)
					cout << "\xDB"; 
				else if (player2x == j && player2y + 3 == i)
					cout << "\xDB"; 
				else
					cout << " ";

				if (j == width - 1)
					cout << "\xB2";
			}
			cout << endl;
		}

		for (int i = 0; i < width + 2; i++)
			cout << "\xB2";
		cout << endl;

		cout << "Player 1: " << score1 << endl << "Player 2: " << score2 << endl;
	}
	void Input()
	{
		ball->Move();

		int ballx = ball->getX();
		int bally = ball->getY();
		int player1x = player1->getX();
		int player2x = player2->getX();
		int player1y = player1->getY();
		int player2y = player2->getY();

		if (_kbhit())
		{
			char current = _getch();
			if (current == up1)
				if (player1y > 0)
					player1->moveUp();
			if (current == up2)
				if (player2y > 0)
					player2->moveUp();
			if (current == down1)
				if (player1y + 4 < width)
					player1->moveDown();
			if (current == down2)
				if (player2y + 4 < width)
					player2->moveDown();

			if (ball->getDirection() == STOP)
				ball->randomDirection();

			if (current == 'q')
				quit = true;
		}
	}
	void Logic()
	{
		int ballx = ball->getX();
		int bally = ball->getY();
		int player1x = player1->getX();
		int player2x = player2->getX();
		int player1y = player1->getY();
		int player2y = player2->getY();

		for (int i = 0; i < 4; i++)
			if (ballx == player1x + 1)
				if (bally == player1y + i)
					ball->changeDirection((eDir)((rand() % 3) + 4));

		for (int i = 0; i < 4; i++)
			if (ballx == player2x - 1)
				if (bally == player2y + i)
					ball->changeDirection((eDir)((rand() % 3) + 1));

		if (bally == width - 1)
			ball->changeDirection(ball->getDirection() == DOWNRIGHT ? UPRIGHT : UPLEFT);
		if (bally == 0)
			ball->changeDirection(ball->getDirection() == UPRIGHT ? DOWNRIGHT : DOWNLEFT);
		if (ballx == width - 1)
			ScoreUp(player1);
		if (ballx == 0)
			ScoreUp(player2);
	}
	void Run()
	{
		while (!quit)
		{
			Draw();
			Input();
			Logic();
		}
	}
};
int main()
{
	cGameManger c(20, 20);
	c.Run();
	return 0;
}
