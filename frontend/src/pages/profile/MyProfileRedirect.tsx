import { Navigate } from "react-router-dom";
import { useAuth } from "../../context/useAuth";

function MyProfileRedirect() {
  const { authState } = useAuth();
  const user = authState.user;

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  return <Navigate to={`/profiles/${user.id}`} replace />;
}

export default MyProfileRedirect;
