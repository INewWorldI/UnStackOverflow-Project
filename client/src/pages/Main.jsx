// import Ask from "../components/question/Ask.jsx";
import QuestionList from "../components/question/QuestionList.jsx";
import Header from "../components/header/Header.jsx";
import Navigation from "../components/navigation/Navigation.jsx";
import Footer from "../components/footer/Footer.jsx";

const Main = () => {
  return (
    <>
      <Header />
      <div className="flex lg:w-[80rem] max-lg:w-full mx-auto ">
        <Navigation className="" />
        <QuestionList className="grow" />
      </div>
      <Footer />
    </>
  );
};

export default Main;
