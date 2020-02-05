using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System;
using System.IO;

namespace EggCrack
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        // cannot click until initial storyboard is completed
        private bool canClick = true;

        public MainWindow()
        {
            InitializeComponent();
            Storyboard juju = (Storyboard)Resources["juju"];
            Storyboard rolling = (Storyboard)Resources["EggRoll"];
            Storyboard poking = (Storyboard)Resources["poking"];
            Storyboard rotten = (Storyboard)Resources["gudetama"];
            Storyboard falling = (Storyboard)Resources["falling"];
            Storyboard drop = (Storyboard)Resources["drop"];
            Storyboard chill = (Storyboard)Resources["chilling"];
            Storyboard stop = (Storyboard)Resources["stop"];
            Storyboard lemmego = (Storyboard)Resources["letmego"];


            // setting everything to hidden
            pokinggrid.Visibility = Visibility.Hidden;
            rottengrid.Visibility = Visibility.Hidden;
            falling_hanging_grid.Visibility = Visibility.Hidden;
            chillgrid.Visibility = Visibility.Hidden;
            stopgrid.Visibility = Visibility.Hidden;
            rollinggrid.Visibility = Visibility.Hidden;
            jujugrid.Visibility = Visibility.Hidden;
            maingude.Visibility = Visibility.Hidden;

            //Keyboard handler to close the window when Escape key is pressed.
            KeyUp += (s, e) =>
            {
                if (e.Key == Key.Escape)
                {
                    Close();
                }
            };

            // need to set the "completed" action before the storyboards begin

            rolling.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                rollinggrid.Visibility = Visibility.Hidden;

            };

            juju.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                jujugrid.Visibility = Visibility.Hidden;
            };

            poking.Completed += (s, e) =>
            {
                maingude.Visibility = Visibility.Visible;
                pokinggrid.Visibility = Visibility.Hidden;
            };

            mainegg.PreviewMouseLeftButtonDown += (s, e) =>
            {
                if (canClick)
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    rollinggrid.Visibility = Visibility.Visible;
                    mainegg.Visibility = Visibility.Hidden;
                    maingude.Visibility = Visibility.Hidden;
                    rolling.Begin();  
                }

                canClick = true;
            };

            maingude.MouseLeftButtonDown += (s, e) =>
            {
                if (canClick)
                {
                    canClick = false;

                    rollinggrid.Visibility = Visibility.Hidden;
                    maingude.Visibility = Visibility.Hidden;
                    jujugrid.Visibility = Visibility.Visible;

                    juju.Begin();
                }
                canClick = true;
            };

            ClickChop.MouseLeftButtonDown += (s, e) =>
            {
                if (canClick)
                {
                    canClick = false;
                    gude.Cursor = Cursors.Arrow;
                    String path = Directory.GetCurrentDirectory();
                    this.Cursor = new Cursor(path+"\\chopsticks.cur");

                    maingude.Visibility = Visibility.Hidden;
                    pokinggrid.Visibility = Visibility.Visible;
                    poking.Begin();
                }
                canClick = true;

            };

        }
    }
}
