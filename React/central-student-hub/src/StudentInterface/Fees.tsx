import React, { useEffect, useState } from 'react';
import './Fees.css'; 
import { RegistrationApi } from '../Services/RegistrationApi.ts';

interface FeesInfo {
  feeAmount: number;
  deadline: string;
}

const Fees: React.FC = () => {
  const [feesInfo, setFeesInfo] = useState<FeesInfo>({
    feeAmount: 0,
    deadline: ''
  });

  const api = new RegistrationApi();
  useEffect(() => {
    const getFees = async () => await api.getFees();
    getFees()
      .then(async (fees) => {
        const date = await api.getDeadline();
        setFeesInfo({
          feeAmount: fees,
          deadline: date.toString()
        })})
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="fees-container">
      <h1 className='exam-title'>Fees Page</h1>
      <div className="fees-info">
        <p>Fee Amount: ${feesInfo.feeAmount.toFixed(2)}</p>
        <p>Deadline: {new Date(feesInfo.deadline).toLocaleDateString()}</p>
      </div>
    </div>
  );
};

export default Fees;
